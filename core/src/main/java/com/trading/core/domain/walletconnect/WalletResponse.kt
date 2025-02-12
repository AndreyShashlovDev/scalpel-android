package com.trading.core.domain.walletconnect

sealed class WalletResponse(open val wallet: Wallet, open val method: String) {

    data class Success(
        override val wallet: Wallet,
        override val method: String,
        val result: String,
    ) : WalletResponse(
        wallet,
        method
    )

    data class Error(
        override val wallet: Wallet,
        override val method: String,
        val code: Int,
        val message: String,
    ) : WalletResponse(
        wallet,
        method
    )
}
