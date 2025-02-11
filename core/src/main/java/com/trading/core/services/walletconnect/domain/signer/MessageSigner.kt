package com.trading.core.services.walletconnect.domain.signer

import com.trading.core.services.walletconnect.domain.Wallet

class SignedMessage(
    val signature: String,
    val wallet: Wallet,
)

interface MessageSigner {
    suspend fun signMessage(msg: String): SignedMessage
}
