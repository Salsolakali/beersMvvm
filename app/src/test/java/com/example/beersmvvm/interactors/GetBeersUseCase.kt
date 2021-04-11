package com.example.beersmvvm

import app.cash.turbine.test
import com.example.beersmvvm.core.domain.ResultOf
import com.example.beersmvvm.features.home.domain.GetBeersUseCase
import com.example.beersmvvm.features.home.domain.repository.DataRepository
import com.example.beersmvvm.interactors.stubs.BeerApiStub
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBeersUseCaseTest {

    private lateinit var sut: GetBeersUseCase

    @Mock
    lateinit var dataRespository: DataRepository

    @Before
    fun setup() {
        initInteractor()
    }

    private fun initInteractor() {
        sut = GetBeersUseCase(dataRespository)
    }

    @Test
    @Throws(Exception::class)
    fun `request repository only once`() {
        sut.execute()
        verify(dataRespository, times(1)).getBeers()
    }

    @Test
    fun `on execute return flow with result`() {
        val remoteObject = BeerApiStub.testServiceData
        val flowResult = flowOf(ResultOf.Success(remoteObject))
        whenever(dataRespository.getBeers()).thenReturn(flowResult)

        val result = sut.execute()
        assertEquals(flowResult, result)
    }

    @Test
    fun `on execute return flow with correct object`() {
        val remoteObject = BeerApiStub.testServiceData
        val flowResult = flowOf(ResultOf.Success(remoteObject))
        whenever(dataRespository.getBeers()).thenReturn(flowResult)

        runBlocking {
            sut.execute().test {
                assertEquals(ResultOf.Success(remoteObject), expectItem())
                expectComplete()
            }
        }
    }
}
