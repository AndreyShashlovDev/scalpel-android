package com.trading.core.domain.storage

interface Storage {
    suspend fun setValue(key: StorageKey, value: String): StorageResult<Unit>
    suspend fun getValue(key: StorageKey): StorageResult<String?>
    suspend fun removeValue(key: StorageKey): StorageResult<Unit>
    suspend fun clear(): StorageResult<Unit>

    suspend fun setInt(key: StorageKey, value: Int): StorageResult<Unit>
    suspend fun getInt(key: StorageKey): StorageResult<Int?>

    suspend fun setLong(key: StorageKey, value: Long): StorageResult<Unit>
    suspend fun getLong(key: StorageKey): StorageResult<Long?>

    suspend fun setBoolean(key: StorageKey, value: Boolean): StorageResult<Unit>
    suspend fun getBoolean(key: StorageKey): StorageResult<Boolean?>
}
