package com.trading.core.data.network.interceptors

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import com.trading.core.domain.network.ApiErrorNotifier
import com.trading.core.domain.network.model.error.ApiError
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiErrorInterceptor @Inject constructor(
    private val errorNotifier: ApiErrorNotifier,
    private val connectivityManager: ConnectivityManager
) : Interceptor {

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            runBlocking {
                errorNotifier.emitError(ApiError.NoInternetConnection)
            }
            throw IOException("No internet connection")
        }

        return try {
            val response = chain.proceed(chain.request())

            when (response.code) {
                401 -> {
                    runBlocking {
                        errorNotifier.emitError(ApiError.Unauthorized)
                    }
                }

                in 400..499 -> {
                    runBlocking {
                        errorNotifier.emitError(
                            ApiError.ServerError(
                                code = response.code, message = response.message
                            )
                        )
                    }
                }

                in 500..599 -> {
                    runBlocking {
                        errorNotifier.emitError(
                            ApiError.ServerError(
                                code = response.code, message = "Server Error"
                            )
                        )
                    }
                }
            }

            response

        } catch (e: SecurityException) {
            runBlocking {
                errorNotifier.emitError(ApiError.NetworkError(e))
            }
            throw e
        } catch (e: IOException) {
            runBlocking {
                errorNotifier.emitError(ApiError.NetworkError(e))
            }
            throw e
        } catch (e: Exception) {
            runBlocking {
                errorNotifier.emitError(ApiError.Unknown(e))
            }
            throw e
        }
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    private fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    }
}