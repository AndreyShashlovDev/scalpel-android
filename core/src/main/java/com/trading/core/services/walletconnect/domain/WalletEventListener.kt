package com.trading.core.services.walletconnect.domain

import kotlinx.coroutines.flow.Flow

interface WalletEventListener {
    fun observeEvents(): Flow<WalletResponse>
}