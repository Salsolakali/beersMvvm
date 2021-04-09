package com.example.beersmvvm.features.home.domain.model

import com.example.beersmvvm.features.home.data.response.BeersResponse


fun BeersResponse.toDomain(beersResponse: BeersResponse): List<Beer> {
    return beersResponse.beers.map {
        Beer(
            it.id,
            it.name,
            it.tagline,
            it.description,
            it.image_url
        )
    }

}
