package com.trading.core.services.data.network.utils

import com.trading.core.services.data.network.model.ApiError
import com.trading.core.services.data.network.model.ApiResponse

fun <T> ApiErrorResolver(response: ApiResponse<T>): ApiError? {
    if (!response.success) {
        if ((response.errors?.size ?: 0) > 0) {
            val code = response.errors?.firstOrNull()?.statusCode ?: -1
            val msg = (response.errors?.map { item -> item.message.joinToString { it } }
                ?: listOf("Unknown error")).joinToString { it }

            return ApiError.ServerError(
                code, msg
            )
        }

        return ApiError.Unknown
    }

    return null
}
