package com.trading.core.data.walletconnect.signer

import com.reown.appkit.client.AppKit
import com.reown.appkit.client.models.request.Request
import com.reown.appkit.client.models.request.SentRequestResult
import com.trading.core.domain.walletconnect.Wallet
import com.trading.core.domain.walletconnect.WalletConnect
import com.trading.core.domain.walletconnect.WalletEventListener
import com.trading.core.domain.walletconnect.WalletResponse
import com.trading.core.domain.walletconnect.signer.MessageSigner
import com.trading.core.domain.walletconnect.signer.SignedMessage
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MessageSignerImpl(
    private val walletEventListener: WalletEventListener,
    private val walletConnect: WalletConnect<Wallet>,
) : MessageSigner {

    override suspend fun signMessage(msg: String): SignedMessage {
        val hexMsg = msg.encodeToByteArray()
            .joinToString(
                separator = "", prefix = "0x"
            ) { eachByte -> "%02x".format(eachByte) }

        val account = walletConnect.getConnection()?.address?.value
            ?: throw Exception("connect wallet before")

        val request = Request(
            "personal_sign",
            "[\"$hexMsg\", \"$account\"]",
        )

        AppKit.request(request = request, onSuccess = { _: SentRequestResult -> }, onError = {})

        try {
            return walletEventListener.observeEvents()
                .filter { item -> item.method == "personal_sign" }
                .map { item ->
                    when (item) {
                        is WalletResponse.Success -> SignedMessage(
                            item.result, item.wallet
                        )

                        is WalletResponse.Error -> throw Exception(item.message)
                    }
                }
                .first()
        } catch (e: Exception) {
            throw e
        }
    }
}
