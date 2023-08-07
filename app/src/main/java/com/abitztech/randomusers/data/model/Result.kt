package com.abitztech.randomusers.data.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    fun execute(
        success: ((T) -> Unit)? = null,
        loading: (() -> Unit)? = null,
        error: ((Throwable) -> Unit)? = null,
        complete: (() -> Unit)? = null
    ) {
        when (this) {
            is Success -> success?.invoke(data)
            is Error -> error?.invoke(exception)
            is Loading -> loading?.invoke()
        }
        complete?.invoke()
    }
}
