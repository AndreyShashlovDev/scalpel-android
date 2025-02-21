package com.trading.core.domain.network.model.error

import com.trading.core.domain.network.model.reponse.ApiResponse

fun <T> apiErrorResolver(response: ApiResponse<T>): ApiError? {
    if (!response.success) {
        if (!(response.errors.isNullOrEmpty())) {
            val code = response.errors.firstOrNull()?.statusCode ?: -1
            val msg = response.errors.map { item -> item.message }
                .joinToString { it }

            return ApiError.ServerError(code, msg)
        }

        return ApiError.Unknown(Exception("something when wrong"))
    }

    return null
}
