package com.example.domain.remote.utils

import kotlinx.coroutines.flow.Flow

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

sealed interface OperationResultDB<out T> {
    class Success<T>(val data: T) : OperationResultDB<Flow<T>>
    class Error(val message: String) : OperationResultDB<Nothing>
    data object Loading : OperationResultDB<Nothing>
}