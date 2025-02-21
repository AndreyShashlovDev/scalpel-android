package com.trading.feature_login.data.api.auth

import com.trading.core.domain.network.model.reponse.ApiResponse
import com.trading.feature_login.data.model.AuthRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @GET("auth/sign-message")
    suspend fun getSignMessage(): ApiResponse<String>

    @POST("auth")
    suspend fun createAuthToken(@Body auth: AuthRequestDto): ApiResponse<String>
}
