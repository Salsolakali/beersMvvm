package com.example.beersmvvm.core.extensions

import com.example.beersmvvm.core.domain.RequestFailure
import com.example.beersmvvm.core.domain.ResultOf

inline fun <reified T> ResultOf<T>.doIfFailure(callback: (requestFailure: RequestFailure) -> Unit) {
    if (this is ResultOf.Failure) {
        callback(requestFailure)
    }
}

inline fun <reified T> ResultOf<T>.doIfSuccess(callback: (value: T) -> Unit) {
    if (this is ResultOf.Success) {
        callback(value)
    }
}

inline fun <reified T> ResultOf<T>.doIfInProgress(callback: () -> Unit) {
    if (this is ResultOf.InProgress) {
        callback()
    }
}