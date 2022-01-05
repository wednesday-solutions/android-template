package com.wednesday.template.domain.weather

import com.wednesday.template.domain.TestException
import com.wednesday.template.domain.base.Result
import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

class FetchFavouriteCitiesWeatherUseCaseImplTest {

    private lateinit var weatherRepo: WeatherRepository
    private lateinit var fetchFavouriteCitiesWeatherUseCase: FetchFavouriteCitiesWeatherUseCaseImpl

    @Before
    fun setUp() {
        weatherRepo = mock()
        fetchFavouriteCitiesWeatherUseCase = FetchFavouriteCitiesWeatherUseCaseImpl(weatherRepo)
    }

    @Test
    fun `Given fetch is successful, When invoke is called, Then Success is returned`() = runBlocking {
        // Given

        // When
        val result = fetchFavouriteCitiesWeatherUseCase(Unit)

        // Then
        assert(result is Result.Success)
        verify(weatherRepo, times(1)).fetchWeatherForFavouriteCities()
        verifyNoMoreInteractions(weatherRepo)
    }

    @Test
    fun `Given fetch is not successful, When invoke is called, Then Error is returned`() = runBlocking {
        // Given
        val testException = TestException()
        whenever(weatherRepo.fetchWeatherForFavouriteCities()).thenThrow(testException)

        // When
        val result = fetchFavouriteCitiesWeatherUseCase(Unit)

        // Then
        assert(result is Result.Error)
        verify(weatherRepo, times(1)).fetchWeatherForFavouriteCities()
        verifyNoMoreInteractions(weatherRepo)
    }
}
