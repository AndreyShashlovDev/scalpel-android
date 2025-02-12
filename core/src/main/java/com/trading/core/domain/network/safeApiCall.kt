package com.trading.core.domain.network

import com.trading.core.domain.network.model.ApiError
import com.trading.core.domain.network.model.ApiResult
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> T,
    transform: (T) -> R,
    errorResolver: (T) -> ApiError? = { null },
): ApiResult<R> {
    return try {
        val response = apiCall()
        errorResolver(response)?.let { return ApiResult.Error(it) }
        ApiResult.Success(transform(response))
    } catch (e: HttpException) {
        ApiResult.Error(
            ApiError.ServerError(
                e.code(), e.message()
            )
        )
    } catch (e: IOException) {
        ApiResult.Error(ApiError.NetworkError(e))
    } catch (e: Exception) {
        Timber.d(
            "dsds", e.message
        )
        ApiResult.Error(ApiError.Unknown)
    }
}
