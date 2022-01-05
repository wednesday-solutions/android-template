package com.wednesday.template.domain.weather

import app.cash.turbine.test
import com.wednesday.template.domain.weather.models.city
import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetFavouriteCitiesFlowUseCaseImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var getFavouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCaseImpl

    @Before
    fun setUp() {
        weatherRepository = mock()
        getFavouriteCitiesFlowUseCase = GetFavouriteCitiesFlowUseCaseImpl(weatherRepository)
    }

    @Test
    fun `Given repository returns flow, When invoke called, Then flow of cities list is returned`() =
        runBlocking {
            // Given
            val cities = listOf(city)
            whenever(weatherRepository.getFavouriteCitiesFlow()).thenReturn(flowOf(cities))

            // When
            getFavouriteCitiesFlowUseCase(Unit).test {
                val result = awaitItem()

                // Then
                assertEquals(expected = cities, actual = result)
                cancelAndIgnoreRemainingEvents()
            }
        }
}
