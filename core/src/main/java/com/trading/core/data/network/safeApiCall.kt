package com.trading.core.data.network

import com.trading.core.domain.network.model.error.ApiError
import com.trading.core.domain.network.model.reponse.ApiResult
import okio.IOException
import retrofit2.HttpException

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
        if (e.code() == 401) {
            return ApiResult.Error(ApiError.Unauthorized)
        }

        ApiResult.Error(ApiError.ServerError(e.code(), e.message()))

    } catch (e: SecurityException) {
        ApiResult.Error(ApiError.NetworkError(e))

    } catch (e: IOException) {
        ApiResult.Error(ApiError.NetworkError(e))

    } catch (e: Exception) {
        ApiResult.Error(ApiError.Unknown(e))
    }
}
