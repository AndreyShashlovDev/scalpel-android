package com.trading.core.domain.network.model.reponse

import com.trading.core.domain.network.model.error.ApiError

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val error: ApiError) : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}
