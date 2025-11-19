package com.example.presentation.components

import com.example.domain.remote.utils.OperationResult


fun <T> OperationResult<T>.handle(
    onSuccess: (data: T) -> Unit,
    onError: (message: String) -> Unit = {},
    onLoading: () -> Unit = {},
) {

    when (this) {
        is OperationResult.Error -> {
            onError(message)
        }

        OperationResult.Loading -> {
           onLoading()
        }

        is OperationResult.Success -> {
            onSuccess(data)
        }
    }
}