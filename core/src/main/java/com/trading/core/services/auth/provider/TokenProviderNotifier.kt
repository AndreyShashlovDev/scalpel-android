package com.trading.core.services.auth.provider

interface TokenProviderNotifier {
    suspend fun updateToken(token: String?)
}
