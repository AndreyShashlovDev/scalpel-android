package com.trading.feature_login.domain.interactor

import android.util.Log
import com.trading.core.domain.auth.TokenProviderNotifier
import com.trading.core.domain.network.model.ApiResult
import com.trading.core.domain.walletconnect.signer.MessageSigner
import com.trading.core.utility.architecture.NoParamsInteractor
import com.trading.feature_login.domain.repository.auth.AuthRepository
import com.trading.feature_login.domain.repository.auth.model.AuthRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeAuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val messageSigner: MessageSigner,
    private val authProvider: TokenProviderNotifier,
) : NoParamsInteractor<Unit> {

    override suspend fun invoke() {
        return withContext(Dispatchers.IO) {
            val msg = authRepository.getSignMessage()

            if (msg is ApiResult.Success) {
                try {
                    val sig = messageSigner.signMessage(msg.data)

                    val token = authRepository.login(
                        AuthRequest(
                            sig.wallet.address, msg.data, sig.signature
                        )
                    )

                    if (token is ApiResult.Success) {
                        authProvider.updateToken(token.data)
                    } else {
                        authProvider.updateToken(null)
                    }
                } catch (e: Exception) {
                    Log.e("MakeAuthInteractor", e.message ?: "unknown error")
                }
            }
        }
    }
}
