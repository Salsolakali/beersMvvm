package com.example.beersmvvm.core.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @field:Json(name = "code")
    var code: Int,
    @field:Json(name = "msg")
    var message: String
)