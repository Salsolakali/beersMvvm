package com.example.beersmvvm.interactors.stubs

import com.example.beersmvvm.features.home.data.response.BeerApi
import com.example.beersmvvm.features.home.domain.model.Beer

object BeerApiStub {
    val testServiceData: List<Beer>
        get() = listOf(getBeer())

    fun getBeer(): Beer {
        return Beer(
            1,
            "Buzz",
            "A Real Bitter Experience.",
            "A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.",
            "https://images.punkapi.com/v2/keg.png")
    }
}