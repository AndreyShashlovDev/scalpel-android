package com.trading.core.domain.network

import com.trading.core.domain.network.model.error.ApiError
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiErrorNotifier @Inject constructor() {
    private val _errors = MutableSharedFlow<ApiError>()
    val errors: SharedFlow<ApiError> = _errors.asSharedFlow()

    suspend fun emitError(error: ApiError) {
        _errors.emit(error)
    }
}
