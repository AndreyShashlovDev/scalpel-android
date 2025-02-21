package com.trading.core.domain.network.model.error

sealed class ApiError {
    data class ServerError(val code: Int, val message: String) : ApiError()
    data class NetworkError(val cause: Exception) : ApiError()
    data object Unauthorized : ApiError()
    data class Unknown(val cause: Exception) : ApiError()
    data object NoInternetConnection : ApiError()
}
