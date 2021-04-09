package com.example.beersmvvm.features.home.domain.model

import java.io.Serializable

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val image: String
) : Serializable {}