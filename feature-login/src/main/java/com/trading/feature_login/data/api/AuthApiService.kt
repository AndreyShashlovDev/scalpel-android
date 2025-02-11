package com.trading.feature_login.data.api

import com.trading.core.services.data.network.model.ApiResponse
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
