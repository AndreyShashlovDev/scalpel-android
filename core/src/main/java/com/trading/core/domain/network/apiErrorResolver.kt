package com.trading.core.domain.network

import com.trading.core.domain.network.model.ApiError
import com.trading.core.domain.network.model.ApiResponse

fun <T> apiErrorResolver(response: ApiResponse<T>): ApiError? {
    if (!response.success) {
        if (!(response.errors.isNullOrEmpty())) {
            val code = response.errors.firstOrNull()?.statusCode ?: -1
            val msg = response.errors.map { item -> item.message.joinToString { it } }
                .joinToString { it }

            return ApiError.ServerError(code, msg)
        }

        return ApiError.Unknown
    }

    return null
}
