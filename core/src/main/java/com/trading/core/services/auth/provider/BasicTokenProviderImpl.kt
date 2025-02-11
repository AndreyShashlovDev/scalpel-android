package com.trading.core.services.auth.provider

import com.trading.core.services.data.local.Storage
import com.trading.core.services.data.local.StorageKey
import com.trading.core.services.data.local.StorageResult
import com.trading.core.services.walletconnect.domain.Wallet
import com.trading.core.services.walletconnect.domain.WalletConnect
import com.trading.core.services.walletconnect.domain.WalletState
import com.trading.core.utility.IOScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasicTokenProviderImpl @Inject constructor(
    private val storage: Storage,
    private val walletConnect: WalletConnect<Wallet>,
) : TokenProvider, TokenProviderNotifier {

    private val tokenStateFlow = MutableStateFlow<String?>(null)

    private val initializationMutex = Mutex()

    @Volatile
    private var initialized: Boolean = false

    init {
        IOScope.launch {
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
}
