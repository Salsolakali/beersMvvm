package com.example.beersmvvm.features.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beersmvvm.core.domain.ResultOf
import com.example.beersmvvm.features.home.domain.GetBeersFilteredUseCase
import com.example.beersmvvm.features.home.domain.GetBeersUseCase
import com.example.beersmvvm.features.home.domain.model.Beer
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeDataViewModel
@Inject constructor(
    private val getBeersUseCase: GetBeersUseCase,
    private val getBeersFilteredUseCase: GetBeersFilteredUseCase
) : ViewModel() {
    var beersResult: MutableLiveData<ResultOf<List<Beer>>> = MutableLiveData()

    private lateinit var jobAll: Job
    private lateinit var jobFiltered: Job

    fun fetchBeers() {
        if(this::jobFiltered.isInitialized){
            jobFiltered.cancel()
        }
        jobAll = viewModelScope.launch {
            getBeersUseCase.execute()
                .onStart { emit(ResultOf.InProgress) }
                .collect { beersResult.postValue(it) }
        }
    }

    fun fetchBeersFiltered(query: String) {
        if(this::jobAll.isInitialized){
            jobAll.cancel()
        }
        jobFiltered = viewModelScope.launch {
            getBeersFilteredUseCase.execute(query)
                .onStart { emit(ResultOf.InProgress) }
                .collect { beersResult.postValue(it) }
        }
    }
}