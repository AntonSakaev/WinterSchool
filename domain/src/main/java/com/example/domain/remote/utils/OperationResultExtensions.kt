package com.example.domain.remote.utils

fun <In, Out> OperationResult<In>.flatMapIfSuccess(
    block: (In) -> OperationResult<Out>
): OperationResult<Out> {
    return when (this) {
        is OperationResult.Success -> block(this.data)
        is OperationResult.Error -> OperationResult.Error(this.message)
        OperationResult.Loading -> OperationResult.Loading
    }
}

fun <T> T.toSuccessResult(): OperationResult.Success<T> = OperationResult.Success(this)


