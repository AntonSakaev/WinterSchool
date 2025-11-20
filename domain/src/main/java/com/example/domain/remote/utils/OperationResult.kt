package com.example.domain.remote.utils

    sealed interface OperationResult<out T> {
        class Success<T>(val data: T) : OperationResult<T>
        class Error(val message: String) : OperationResult<Nothing>
        data object Loading : OperationResult<Nothing>
    }

fun <T, R> OperationResult<T>.map(transform: (T) -> R): OperationResult<R> {
    return when (this) {
        is OperationResult.Success -> OperationResult.Success(transform(this.data))
        is OperationResult.Error -> this
        is OperationResult.Loading -> this
    }
}