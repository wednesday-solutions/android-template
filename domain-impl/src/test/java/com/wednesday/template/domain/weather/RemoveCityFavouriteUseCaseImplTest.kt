package com.wednesday.template.domain.weather

import com.wednesday.template.domain.TestException
import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.models.city
import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.same
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class RemoveCityFavouriteUseCaseImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var removeCityFavouriteUseCase: RemoveCityFavouriteUseCaseImpl

    @Before
    fun setUp() {
        weatherRepository = mock()
        removeCityFavouriteUseCase = RemoveCityFavouriteUseCaseImpl(weatherRepository)
    }

    @Test
    fun `Given city removed as fav by repo, When invoke called, Then Success is returned`(): Unit =
        runBlocking {
            // Given
            val city = city
            whenever(weatherRepository.removeCityAsFavourite(city)).thenReturn(Unit)

            // When
            val result = removeCityFavouriteUseCase(city)

            // Then
            assertTrue(result is Result.Success)
            verify(weatherRepository, times(1)).removeCityAsFavourite(same(city))
            verifyNoMoreInteractions(weatherRepository)
        }

    @Test
    fun `Given repo throws exception, When invoke called, Then Error is returned`(): Unit =
        runBlocking {
            // Given
            val city = city
            val testException = TestException()
            whenever(weatherRepository.removeCityAsFavourite(city)).thenThrow(testException)

            // When
            val result = removeCityFavouriteUseCase(city)

            // Then
            assertTrue(result is Result.Error)
            verify(weatherRepository, times(1)).removeCityAsFavourite(same(city))
            verifyNoMoreInteractions(weatherRepository)
        }
}
