package com.trading.feature_login.domain.repository.auth

import com.trading.core.services.data.network.model.ApiResult
import com.trading.feature_login.domain.repository.auth.model.AuthRequest

interface AuthRepository {
    suspend fun getSignMessage(): ApiResult<String>
    suspend fun login(auth: AuthRequest): ApiResult<String>
}
