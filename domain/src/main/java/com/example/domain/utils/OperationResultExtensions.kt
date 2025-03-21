package com.example.domain.utils




fun <In, Out> OperationResult<In>.flatMapIfSuccess(
    block: (In) -> OperationResult<Out>
): OperationResult<Out> {
    return when (this) {
        is OperationResult.Success -> block(this.data)
        is OperationResult.Error -> OperationResult.Error(this.message)
        OperationResult.Loading -> OperationResult.Loading
    }
}

/** Преобразует [T] в [OperationResult.Success] */
fun <T> T.toSuccessResult(): OperationResult.Success<T> = OperationResult.Success(this)