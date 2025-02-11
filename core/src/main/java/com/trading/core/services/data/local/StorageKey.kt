package com.trading.core.services.data.local

sealed class StorageKey(val key: String) {
    data object AuthToken : StorageKey("auth_token")
    data object MainWalletAddress : StorageKey("main_wallet_address")
}
