package com.trading.core.domain.auth

import com.trading.core.domain.network.ApiErrorNotifier
import com.trading.core.domain.network.model.error.ApiError
import com.trading.core.domain.storage.Storage
import com.trading.core.domain.storage.StorageKey
import com.trading.core.domain.storage.StorageResult
import com.trading.core.domain.walletconnect.Wallet
import com.trading.core.domain.walletconnect.WalletConnect
import com.trading.core.domain.walletconnect.WalletState
import com.trading.core.utility.AppScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasicTokenProviderImpl @Inject constructor(
    private val storage: Storage,
    private val walletConnect: WalletConnect<Wallet>,
    private val errorNotifier: ApiErrorNotifier,
) : TokenProvider, TokenProviderNotifier {

    private val tokenStateFlow = MutableStateFlow<String?>(null)

    private val initializationMutex = Mutex()

    @Volatile
    private var initialized: Boolean = false

    init {
        AppScope.io.launch {
            try {
                walletConnect.observe()
                    .collect { state ->
                        if (state is WalletState.WalletChanged && state.wallet == null && tokenStateFlow.value != null) {
                            updateToken(null)
                        }
                    }
            } catch (e: Exception) {
                println(e)
            }
        }

        AppScope.io.launch {
            errorNotifier.errors.collect { error ->
                if (error is ApiError.Unauthorized && tokenStateFlow.value != null) {
                    updateToken(null)
                }
            }
        }
    }

    override fun isInitialized(): Boolean = initialized

    override suspend fun initialize() {
        if (!initialized) {
            initializationMutex.withLock {
                if (!initialized) {
                    val keyReadResult = storage.getValue(StorageKey.AuthToken)

                    if (keyReadResult is StorageResult.Success && keyReadResult.data != null) {
                        tokenStateFlow.emit(keyReadResult.data)
                    }
                    initialized = true
                }
            }
        }
    }

    override fun getToken(): String? = tokenStateFlow.value

    override suspend fun updateToken(token: String?) {
        tokenStateFlow.emit(token)

        if (token != null) {
            storage.setValue(StorageKey.AuthToken, token)
        } else {
            storage.removeValue(StorageKey.AuthToken)
        }
    }

    override fun observe(): Flow<String?> = tokenStateFlow.asStateFlow()
}
