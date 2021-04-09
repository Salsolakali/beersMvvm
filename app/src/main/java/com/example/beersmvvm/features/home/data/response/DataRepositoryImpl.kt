package com.example.beersmvvm.features.home.data.response

import com.example.beersmvvm.core.data.APIService
import com.example.beersmvvm.core.data.FailureFactory
import com.example.beersmvvm.core.domain.ResultOf
import com.example.beersmvvm.core.extensions.safeCall
import com.example.beersmvvm.features.home.domain.model.Beer
import com.example.beersmvvm.features.home.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class DataRepositoryImpl(private val apiService: APIService) : DataRepository {
    override fun getBeers(): Flow<ResultOf<List<Beer>>> = flow {
        emit(apiService.getBeers().safeCall({ response -> response.map { it.toDomain() } }))
    }.catch {
        emit(FailureFactory().handleException(it))
    }

    override fun getBeersFiltered(query: String): Flow<ResultOf<List<Beer>>> = flow {
        emit(apiService.getBeersFiltered(query).safeCall({ response -> response.map { it.toDomain() } }))
    }.catch {
        emit(FailureFactory().handleException(it))
    }
}
