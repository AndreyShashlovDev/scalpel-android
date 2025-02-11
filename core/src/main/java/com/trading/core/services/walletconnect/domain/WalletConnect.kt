package com.trading.core.services.walletconnect.domain

import com.trading.core.utility.evm.Address
import kotlinx.coroutines.flow.Flow

interface Wallet {
    val address: Address
}

sealed class WalletState<T : Wallet>(open val wallet: T?) {
    data class Initialization<T : Wallet>(override val wallet: T? = null) : WalletState<T>(wallet)
    data class WalletChanged<T : Wallet>(override val wallet: T? = null) : WalletState<T>(wallet)
}


abstract class WalletConnect<T : Wallet> {

    abstract fun isInitialized(): Flow<Boolean>

    abstract fun isConnected(): Boolean

    abstract fun connect()

    abstract fun disconnect()

    abstract fun getConnection(): T?

    abstract fun observe(): Flow<WalletState<T>>
}
