package com.example.beersmvvm.features.home.data.response

import com.example.beersmvvm.features.home.domain.model.Beer
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerApi(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("first_brewed") val first_brewed: String?,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val image_url: String,
    @SerializedName("abv") val abv: Double?,
    @SerializedName("ibu") val ibu: Double?,
    @SerializedName("target_fg") val target_fg: Double?,
    @SerializedName("target_og") val target_og: Double?,
    @SerializedName("ebc") val ebc: Double?,
    @SerializedName("srm") val srm: Double?,
    @SerializedName("ph") val ph: Double?,
    @SerializedName("attenuation_level") val attenuation_level: Double
) {
    fun toDomain(): Beer {
        return Beer(
            id,
            name,
            tagline,
            description,
            image_url
        )
    }
}