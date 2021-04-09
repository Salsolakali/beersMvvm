package com.example.beersmvvm.features.home.domain.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val image: String
):Parcelable{}