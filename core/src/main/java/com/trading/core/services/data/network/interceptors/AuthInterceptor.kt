package com.trading.core.services.data.network.interceptors

import com.trading.core.services.auth.provider.TokenProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(private val tokenProvider: TokenProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestOriginal = chain.request()

        if (!tokenProvider.isInitialized()) {
            runBlocking {
                tokenProvider.initialize()
            }
        }

        if (tokenProvider.getToken() != null) {
            val request = requestOriginal.newBuilder()
                .addHeader("Authorization", "Bearer ${tokenProvider.getToken()}").build()

            return chain.proceed(request)
        }

        return chain.proceed(requestOriginal)
    }
}
