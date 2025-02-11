package com.trading.core.services.walletconnect.domain

import com.trading.core.utility.evm.Address

open class SimpleEvmWallet(override val address: Address) : Wallet
