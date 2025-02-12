package com.trading.core.domain.walletconnect.signer

import com.trading.core.domain.walletconnect.Wallet

class SignedMessage(
    val signature: String,
    val wallet: Wallet,
)

interface MessageSigner {
    suspend fun signMessage(msg: String): SignedMessage
}
