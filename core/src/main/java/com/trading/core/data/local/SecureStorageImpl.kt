package com.trading.core.data.local

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.trading.core.domain.storage.Storage
import com.trading.core.domain.storage.StorageKey
import com.trading.core.domain.storage.StorageResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
import javax.inject.Inject

@Serializable
data class SecureStorage(
    val values: MutableMap<String, String> = mutableMapOf(),
)

class SecureStorageImpl @Inject constructor(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : Storage {

    companion object {
        private const val FILE_NAME = "storage"
    }

    private val writeMutex = Mutex()

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private fun getMasterKey(): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private fun getEncryptedFile(): EncryptedFile {
        val masterKey = getMasterKey()
        val file = File(
            context.filesDir, FILE_NAME
        )

        return EncryptedFile.Builder(
            context, file, masterKey, EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        )
            .build()
    }

    private suspend fun readStorage(): StorageResult<SecureStorage> = withContext(dispatcher) {
        try {
            val file = File(
                context.filesDir, FILE_NAME
            )

            if (!file.exists()) {
                return@withContext StorageResult.Success(SecureStorage())
            }

            try {
                val result = getEncryptedFile().openFileInput()
                    .use { input ->
                        val jsonString = String(
                            input.readBytes(), Charsets.UTF_8
                        )
                        json.decodeFromString(
                            SecureStorage.serializer(), jsonString
                        )
                    }
                StorageResult.Success(result)
            } catch (e: IOException) {
                file.delete()
                StorageResult.Success(SecureStorage())
            }
        } catch (e: SecurityException) {
            StorageResult.Error.SecurityError(e)
        } catch (e: SerializationException) {
            StorageResult.Error.SerializationError(e)
        } catch (e: Exception) {
            StorageResult.Error.SecurityError(e)
        }
    }

    private suspend fun writeStorage(storage: SecureStorage): StorageResult<Unit> =
        withContext(dispatcher) {
            try {
                val file = File(context.filesDir, FILE_NAME)

                if (file.exists()) {
                    file.delete()
                }

                getEncryptedFile().openFileOutput()
                    .use { output ->
                        val jsonString = json.encodeToString(
                            SecureStorage.serializer(), storage
                        )
                        output.write(jsonString.toByteArray(Charsets.UTF_8))
                    }
                StorageResult.Success(Unit)
            } catch (e: SecurityException) {
                StorageResult.Error.SecurityError(e)
            } catch (e: IOException) {
                StorageResult.Error.FileError(e)
            } catch (e: SerializationException) {
                StorageResult.Error.SerializationError(e)
            } catch (e: Exception) {
                StorageResult.Error.SecurityError(e)
            }
        }

    override suspend fun setValue(key: StorageKey, value: String): StorageResult<Unit> {
        writeMutex.withLock {
            when (val storageResult = readStorage()) {
                is StorageResult.Success -> {
                    storageResult.data.values[key.key] = value
                    return writeStorage(storageResult.data)
                }

                is StorageResult.Error -> return storageResult
            }
        }
    }

    override suspend fun getValue(key: StorageKey): StorageResult<String?> {
        return when (val storageResult = readStorage()) {
            is StorageResult.Success -> StorageResult.Success(storageResult.data.values[key.key])
            is StorageResult.Error -> storageResult
        }
    }

    override suspend fun removeValue(key: StorageKey): StorageResult<Unit> {
        when (val storageResult = readStorage()) {
            is StorageResult.Success -> {
                storageResult.data.values.remove(key.key)
                return writeStorage(storageResult.data)
            }

            is StorageResult.Error -> return storageResult
        }
    }

    override suspend fun clear(): StorageResult<Unit> = withContext(dispatcher) {
        try {
            File(
                context.filesDir, FILE_NAME
            ).delete()
            StorageResult.Success(Unit)
        } catch (e: SecurityException) {
            StorageResult.Error.SecurityError(e)
        } catch (e: IOException) {
            StorageResult.Error.FileError(e)
        } catch (e: Exception) {
            StorageResult.Error.SecurityError(e)
        }
    }

    override suspend fun setInt(key: StorageKey, value: Int): StorageResult<Unit> = setValue(
        key, value.toString()
    )

    override suspend fun getInt(key: StorageKey): StorageResult<Int?> =
        when (val result = getValue(key)) {
            is StorageResult.Success -> StorageResult.Success(result.data?.toIntOrNull())
            is StorageResult.Error -> result
        }

    override suspend fun setLong(key: StorageKey, value: Long): StorageResult<Unit> = setValue(
        key, value.toString()
    )

    override suspend fun getLong(key: StorageKey): StorageResult<Long?> =
        when (val result = getValue(key)) {
            is StorageResult.Success -> StorageResult.Success(result.data?.toLongOrNull())
            is StorageResult.Error -> result
        }

    override suspend fun setBoolean(key: StorageKey, value: Boolean): StorageResult<Unit> =
        setValue(
            key, value.toString()
        )

    override suspend fun getBoolean(key: StorageKey): StorageResult<Boolean?> =
        when (val result = getValue(key)) {
            is StorageResult.Success -> StorageResult.Success(result.data?.toBooleanStrictOrNull())
            is StorageResult.Error -> result
        }
}
