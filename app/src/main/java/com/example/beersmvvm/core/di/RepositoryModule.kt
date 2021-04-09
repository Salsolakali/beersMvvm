package com.example.beersmvvm.core.di

import com.example.beersmvvm.core.data.APIService
import com.example.beersmvvm.features.home.data.response.DataRepositoryImpl
import com.example.beersmvvm.features.home.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesBeersRepository(apiservice: APIService): DataRepository {
        return DataRepositoryImpl(apiservice)
    }
}