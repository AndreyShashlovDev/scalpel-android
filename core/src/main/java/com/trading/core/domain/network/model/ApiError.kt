package com.trading.core.domain.network.model

sealed class ApiError : Throwable() {
    data class ServerError(val code: Int, override val message: String) : ApiError()
    data class NetworkError(override val cause: Throwable) : ApiError()
    data object Unauthorized : ApiError()
    data object Unknown : ApiError()
}
