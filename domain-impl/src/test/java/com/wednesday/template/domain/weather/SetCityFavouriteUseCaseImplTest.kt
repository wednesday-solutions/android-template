package com.wednesday.template.domain.weather

import com.wednesday.template.domain.TestException
import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.models.city
import com.wednesday.template.repo.weather.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.same
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SetCityFavouriteUseCaseImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var setCityFavouriteUseCase: SetCityFavouriteUseCaseImpl

    @Before
    fun setUp() {
        weatherRepository = mock()
        setCityFavouriteUseCase = SetCityFavouriteUseCaseImpl(weatherRepository)
    }

    @Test
    fun `Given city searched by repo, When invoke called, Then Success is returned`(): Unit =
        runTest {
            // Given
            val city = city
            whenever(weatherRepository.setCityAsFavourite(city)).thenReturn(Unit)

            // When
            val result = setCityFavouriteUseCase(city)

            // Then
            kotlin.test.assertTrue(result is Result.Success)
            verify(weatherRepository, times(1)).setCityAsFavourite(same(city))
            verifyNoMoreInteractions(weatherRepository)
        }

    @Test
    fun `Given repo throws exception, When invoke called, Then Error is returned`(): Unit =
        runTest {
            // Given
            val city = city
            val testException = TestException()
            whenever(weatherRepository.setCityAsFavourite(city)).thenThrow(testException)

            // When
            val result = setCityFavouriteUseCase(city)

            // Then
            kotlin.test.assertTrue(result is Result.Error)
            verify(weatherRepository, times(1)).setCityAsFavourite(same(city))
            verifyNoMoreInteractions(weatherRepository)
        }
}
