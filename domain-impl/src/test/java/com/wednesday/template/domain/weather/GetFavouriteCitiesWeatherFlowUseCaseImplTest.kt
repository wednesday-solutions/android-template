package com.wednesday.template.domain.weather

import app.cash.turbine.test
import com.wednesday.template.domain.weather.models.weather
import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetFavouriteCitiesWeatherFlowUseCaseImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getFavouriteCitiesWeatherFlowUseCase: GetFavouriteCitiesWeatherFlowUseCaseImpl

    @Before
    fun setUp() {
        weatherRepository = mock()
        getFavouriteCitiesWeatherFlowUseCase =
            GetFavouriteCitiesWeatherFlowUseCaseImpl(weatherRepository)
    }

    @Test
    fun `Given flow from repo, When invoke is called, Then flow of weather list is returned`(): Unit =
        runBlocking {
            // Given
            val weatherList = listOf(weather)
            whenever(weatherRepository.getFavouriteCitiesWeatherFlow())
                .thenReturn(flowOf(weatherList))

            // When
            getFavouriteCitiesWeatherFlowUseCase(Unit).test {
                val result = awaitItem()

                // Then
                assertEquals(expected = weatherList, actual = result)
                cancelAndIgnoreRemainingEvents()
            }
            verify(weatherRepository, times(1)).getFavouriteCitiesWeatherFlow()
            verifyNoMoreInteractions(weatherRepository)
        }
}
