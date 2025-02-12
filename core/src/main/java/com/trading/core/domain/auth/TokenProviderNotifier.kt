package com.trading.core.domain.auth

interface TokenProviderNotifier {
    suspend fun updateToken(token: String?)
}
