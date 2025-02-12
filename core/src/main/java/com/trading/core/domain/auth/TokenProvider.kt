package com.trading.core.domain.auth

import kotlinx.coroutines.flow.Flow

interface TokenProvider {
    fun isInitialized(): Boolean
    suspend fun initialize()
    fun getToken(): String?
    fun observe(): Flow<String?>
}