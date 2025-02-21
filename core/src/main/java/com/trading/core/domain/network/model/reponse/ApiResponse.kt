package com.trading.core.domain.network.model.reponse

import com.trading.core.domain.network.model.error.ApiErrorResponse
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val errors: Array<ApiErrorResponse>? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ApiResponse<*>

        if (success != other.success) return false
        if (data != other.data) return false
        if (errors != null) {
            if (other.errors == null) return false
            if (!errors.contentEquals(other.errors)) return false
        } else if (other.errors != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = success.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (errors?.contentHashCode() ?: 0)
        return result
    }
}
