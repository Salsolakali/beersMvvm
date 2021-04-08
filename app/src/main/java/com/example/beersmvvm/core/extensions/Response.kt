package com.example.beersmvvm.core.extensions

import com.example.beersmvvm.core.data.FailureFactory
import com.example.beersmvvm.core.domain.ResultOf
import retrofit2.Response

fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorFactory: FailureFactory = FailureFactory()
): ResultOf<R> {
    val body = body()
    return when {
        isSuccessful && body != null -> ResultOf.Success(transform(body))
        else -> errorFactory.handleCode(code = code(), errorBody = errorBody())
    }
}