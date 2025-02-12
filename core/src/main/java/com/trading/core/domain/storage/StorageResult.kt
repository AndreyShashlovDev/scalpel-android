package com.trading.core.domain.storage

sealed class StorageResult<out T> {
    data class Success<T>(val data: T) : StorageResult<T>()

    sealed class Error : StorageResult<Nothing>() {
        data class SecurityError(val exception: Exception) : Error()
        data class FileError(val exception: Exception) : Error()
        data class SerializationError(val exception: Exception) : Error()
    }
}
