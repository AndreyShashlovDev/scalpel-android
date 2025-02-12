package com.trading.feature_login.data.repository

import com.trading.core.domain.network.model.ApiResult
import com.trading.core.domain.network.apiErrorResolver
import com.trading.core.domain.network.safeApiCall
import com.trading.feature_login.data.api.auth.AuthApiService
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
                           errorResolver = { apiErrorResolver(it) },
                           transform = { it.data!! })
    }

    override suspend fun login(auth: AuthRequest): ApiResult<String> {
        val dto = authMapper.mapToCreateDto(auth)

        return safeApiCall(
            { apiService.createAuthToken(dto) },
            errorResolver = { apiErrorResolver(it) },
            transform = { it.data!! }
        )
    }
}
