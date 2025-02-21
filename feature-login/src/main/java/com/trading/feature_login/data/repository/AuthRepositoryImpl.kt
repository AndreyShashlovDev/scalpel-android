package com.trading.feature_login.data.repository

import com.trading.core.data.network.safeApiCall
import com.trading.core.domain.network.model.error.apiErrorResolver
import com.trading.core.domain.network.model.reponse.ApiResult
import com.trading.feature_login.data.api.auth.AuthApiService
import com.trading.feature_login.data.mapper.AuthMapper
import com.trading.feature_login.domain.repository.auth.AuthRepository
import com.trading.feature_login.domain.repository.auth.model.AuthRequest
import javax.inject.Inject

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

        return safeApiCall({ apiService.createAuthToken(dto) },
                           errorResolver = { apiErrorResolver(it) },
                           transform = { it.data!! })
    }
}
