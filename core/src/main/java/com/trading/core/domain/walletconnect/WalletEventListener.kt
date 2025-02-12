package com.trading.core.domain.walletconnect

import kotlinx.coroutines.flow.Flow

interface WalletEventListener {
    fun observeEvents(): Flow<WalletResponse>
}