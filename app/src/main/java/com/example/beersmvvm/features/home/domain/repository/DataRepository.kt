package com.example.beersmvvm.features.home.domain.repository

import com.example.beersmvvm.core.domain.ResultOf
import com.example.beersmvvm.features.home.domain.model.Beer
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getBeers(): Flow<ResultOf<List<Beer>>>
    fun getBeersFiltered(query: String): Flow<ResultOf<List<Beer>>>
}