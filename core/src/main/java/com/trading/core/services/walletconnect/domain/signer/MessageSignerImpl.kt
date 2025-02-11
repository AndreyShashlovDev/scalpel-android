package com.trading.core.services.walletconnect.domain.signer

import com.reown.appkit.client.AppKit
import com.reown.appkit.client.models.request.Request
import com.reown.appkit.client.models.request.SentRequestResult
import com.trading.core.services.walletconnect.domain.Wallet
import com.trading.core.services.walletconnect.domain.WalletConnect
import com.trading.core.services.walletconnect.domain.WalletEventListener
import com.trading.core.services.walletconnect.domain.WalletResponse
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class MessageSignerImpl @Inject constructor(
    private val walletEventListener: WalletEventListener,
    private val walletConnect: WalletConnect<Wallet>,
) : MessageSigner {

    override suspend fun signMessage(msg: String): SignedMessage {
        val hexMsg = msg.encodeToByteArray().joinToString(
            separator = "", prefix = "0x"
        ) { eachByte -> "%02x".format(eachByte) }

        val account =
            walletConnect.getConnection()?.address?.value ?: throw Error("connect wallet before")

        val request = Request(
            "personal_sign",
            "[\"$hexMsg\", \"$account\"]",
        )

        AppKit.request(request = request, onSuccess = { result: SentRequestResult ->
            Timber.d("sd", result)
        }, onError = {
            Timber.e("sdsd", it)
        })

        return walletEventListener.observeEvents().filter { item -> item.method == "personal_sign" }
            .map { item ->
                when (item) {
                    is WalletResponse.Success -> SignedMessage(
                        item.result, item.wallet
                    )

                    is WalletResponse.Error -> throw Error(item.message)
                }
            }.first()
    }
}
