package com.trading.core.services.auth.provider

interface TokenProvider {
    fun isInitialized(): Boolean
    suspend fun initialize()
    fun getToken(): String?
}