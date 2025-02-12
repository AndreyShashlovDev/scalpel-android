package com.trading.core.domain.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    val statusCode: Int,
    val error: String,
    val message: Array<String>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ApiErrorResponse

        if (statusCode != other.statusCode) return false
        if (error != other.error) return false
        if (!message.contentEquals(other.message)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = statusCode
        result = 31 * result + error.hashCode()
        result = 31 * result + message.contentHashCode()
        return result
    }
}
