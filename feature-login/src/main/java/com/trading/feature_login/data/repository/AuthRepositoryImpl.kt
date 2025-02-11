package com.trading.feature_login.data.repository

import com.trading.core.services.data.network.model.ApiResult
import com.trading.core.services.data.network.utils.ApiErrorResolver
import com.trading.core.services.data.network.utils.safeApiCall
import com.trading.feature_login.data.api.AuthApiService
import com.trading.feature_login.data.mapper.AuthMapper
import com.trading.feature_login.domain.repository.auth.AuthRepository
import com.trading.feature_login.domain.repository.auth.model.AuthRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val authMapper: AuthMapper,
) : AuthRepository {

    override suspend fun getSignMessage(): ApiResult<String> {
        return safeApiCall({ apiService.getSignMessage() },
                           errorResolver = { ApiErrorResolver(it) },
                           transform = { it.data!! })
    }

    override suspend fun login(auth: AuthRequest): ApiResult<String> {
        val dto = authMapper.mapToCreateDto(auth)

        return safeApiCall(
            { apiService.createAuthToken(dto) },
            errorResolver = { ApiErrorResolver(it) },
            transform = { it.data!! }
        )
    }
}
