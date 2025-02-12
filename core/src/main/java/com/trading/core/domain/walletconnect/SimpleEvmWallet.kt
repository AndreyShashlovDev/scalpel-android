package com.trading.core.domain.walletconnect

import com.trading.core.utility.evm.Address

open class SimpleEvmWallet(override val address: Address) : Wallet
