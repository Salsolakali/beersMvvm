package com.example.beersmvvm.features.home.domain

import com.example.beersmvvm.features.home.domain.repository.DataRepository
import javax.inject.Inject

class GetBeersUseCase
@Inject constructor(private val repository: DataRepository) {
    fun execute() = repository.getBeers()
}