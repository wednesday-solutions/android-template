package com.wednesday.template.repo.weather

import app.cash.turbine.test
import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.weather.models.cityMappedFromLocalCity
import com.wednesday.template.repo.weather.models.cityMappedFromRemoteCity
import com.wednesday.template.repo.weather.models.localCity
import com.wednesday.template.repo.weather.models.localWeather
import com.wednesday.template.repo.weather.models.remoteCity
import com.wednesday.template.repo.weather.models.remoteWeather
import com.wednesday.template.repo.weather.models.weather
import com.wednesday.template.service.weather.OpenWeatherLocalService
import com.wednesday.template.service.weather.OpenWeatherRemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.same
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalTime
class WeatherRepositoryImplTest {

    private lateinit var weatherRemoteService: OpenWeatherRemoteService
    private lateinit var weatherLocalService: OpenWeatherLocalService
    private lateinit var domainCityMapper: DomainCityMapper
    private lateinit var localCityMapper: LocalCityMapper
    private lateinit var localWeatherMapper: LocalWeatherMapper
    private lateinit var domainWeatherMapper: DomainWeatherMapper
    private lateinit var dateRepo: DateRepo
    private lateinit var weatherRepository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        weatherRemoteService = mock()
        weatherLocalService = mock()
        domainCityMapper = mock()
        localCityMapper = mock()
        localWeatherMapper = mock()
        domainWeatherMapper = mock()
        dateRepo = mock()

        weatherRepository = WeatherRepositoryImpl(
            weatherRemoteService,
            weatherLocalService,
            domainCityMapper,
            localCityMapper,
            localWeatherMapper,
            domainWeatherMapper,
            dateRepo,
        )
    }

    private fun verifyNoMoreInteractions() {
        org.mockito.kotlin.verifyNoMoreInteractions(
            weatherRemoteService,
            weatherLocalService,
            domainCityMapper,
            localCityMapper,
            localWeatherMapper,
            domainWeatherMapper,
            dateRepo,
        )
    }

    @Test
    fun `Given a search string, When searchCities is called, Then list of cities is returned`(): Unit =
        runTest {
            // Given
            val searchTerm = "Pune"
            val remoteCities = listOf(remoteCity)
            whenever(weatherRemoteService.geocodingSearch(searchTerm))
                .thenReturn(remoteCities)
            whenever(domainCityMapper.mapRemoteCity(same(remoteCities)))
                .thenReturn(listOf(cityMappedFromRemoteCity))

            // When
            val result = weatherRepository.searchCities(searchTerm)

            // Then
            assertEquals(expected = listOf(cityMappedFromRemoteCity), actual = result)
            verify(weatherRemoteService, times(1)).geocodingSearch(searchTerm)
            verify(domainCityMapper, times(1)).mapRemoteCity(same(remoteCities))
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given getFavouriteCitiesFlow is called, Then it returns flow of cities`(): Unit =
        runTest {
            // Given
            val localCities = listOf(localCity)
            val cities = listOf(cityMappedFromLocalCity)
            whenever(weatherLocalService.getFavoriteCitiesFlow()).thenReturn(
                flowOf(
                    localCities,
                    localCities,
                ),
            )
            whenever(domainCityMapper.map(localCities)).thenReturn(cities)

            // When
            weatherRepository.getFavouriteCitiesFlow().test {
                val result1 = awaitItem()

                // Then
                assertEquals(expected = cities, actual = result1)
                cancelAndIgnoreRemainingEvents()
            }
            verify(weatherLocalService, times(1)).getFavoriteCitiesFlow()
            verify(domainCityMapper, times(2)).map(localCities)
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given getFavouriteCitiesList is called, Then it returns list of cities`(): Unit =
        runTest {
            // Given
            val localCities = listOf(localCity)
            val cities = listOf(cityMappedFromLocalCity)
            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
            whenever(domainCityMapper.map(localCities)).thenReturn(cities)

            // When
            val result = weatherRepository.getFavouriteCitiesList()

            // Then
            assertEquals(expected = cities, actual = result)
            verify(weatherLocalService, times(1)).getFavoriteCities()
            verify(domainCityMapper, times(1)).map(localCities)
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given setCityAsFavourite called, Then it marks the city favourite`(): Unit = runTest {
        // Given
        val city = cityMappedFromLocalCity
        val localCity = localCity
        whenever(localCityMapper.map(city)).thenReturn(localCity)

        // When
        weatherRepository.setCityAsFavourite(city)

        // Then
        verify(weatherLocalService, times(1)).markCityAsFavorite(same(localCity))
        verify(localCityMapper, times(1)).map(same(city))
    }

    @Test
    fun `Given removeCityAsFavourite called, Then it marks the city favourite`(): Unit =
        runTest {
            // Given
            val city = cityMappedFromLocalCity
            val localCity = localCity
            whenever(localCityMapper.map(city)).thenReturn(localCity)

            // When
            weatherRepository.removeCityAsFavourite(city)

            // Then
            verify(weatherLocalService, times(1)).deleteFavoriteCity(same(localCity))
            verify(localCityMapper, times(1)).map(same(city))
        }

    @Test
    fun `Given fetchWeatherForFavouriteCities, When weather list is stale, Then weather data is refreshed`(): Unit =
        runTest {
            // Given
            val nowDateTimeAsLong = JavaDate.from(Instant.ofEpochSecond(3 * 60 * 60)).time
            val localWeather = localWeather
            val localCities = listOf(localCity)
            whenever(dateRepo.nowDateTimeAsLong()).thenReturn(nowDateTimeAsLong)
            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
            whenever(weatherLocalService.getLocalCurrentWeather(any(), any())).thenReturn(
                localWeather,
            )
            whenever(weatherRemoteService.currentWeather(any())).thenReturn(remoteWeather)
            whenever(localWeatherMapper.map(same(remoteWeather), any(), any())).thenReturn(
                localWeather,
            )
            whenever(weatherLocalService.upsertLocalCurrentWeather(localWeather)).thenReturn(Unit)

            // When
            weatherRepository.fetchWeatherForFavouriteCities()

            // Then
            verify(dateRepo, times(1)).nowDateTimeAsLong()
            verify(weatherLocalService, times(1)).getFavoriteCities()
            verify(weatherLocalService, times(1)).getLocalCurrentWeather(any(), any())
            verify(weatherRemoteService, times(1)).currentWeather(any())
            verify(localWeatherMapper, times(1)).map(same(remoteWeather), any(), any())
            verify(weatherLocalService, times(1)).upsertLocalCurrentWeather(localWeather)
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given fetchWeatherForFavouriteCities, When weather list is not stale, Then weather data is not refreshed`(): Unit =
        runTest {
            // Given
            val nowDateTimeAsLong = JavaDate.from(Instant.EPOCH).time
            val localWeather = localWeather
            val localCities = listOf(localCity)
            whenever(dateRepo.nowDateTimeAsLong()).thenReturn(nowDateTimeAsLong)
            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
            whenever(weatherLocalService.getLocalCurrentWeather(any(), any())).thenReturn(
                localWeather,
            )
            whenever(weatherRemoteService.currentWeather(any())).thenReturn(remoteWeather)
            whenever(localWeatherMapper.map(same(remoteWeather), any(), any())).thenReturn(
                localWeather,
            )
            whenever(weatherLocalService.upsertLocalCurrentWeather(localWeather)).thenReturn(Unit)

            // When
            weatherRepository.fetchWeatherForFavouriteCities()

            // Then
            verify(dateRepo, times(1)).nowDateTimeAsLong()
            verify(weatherLocalService, times(1)).getFavoriteCities()
            verify(weatherLocalService, times(1)).getLocalCurrentWeather(any(), any())
            verify(weatherRemoteService, never()).currentWeather(any())
            verify(localWeatherMapper, never()).map(same(remoteWeather), any(), any())
            verify(weatherLocalService, never()).upsertLocalCurrentWeather(localWeather)
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given fetchWeatherForFavouriteCities, When favourite cities is empty, Then nothing happens`(): Unit =
        runTest {
            // Given
            val todayDate = JavaDate.from(Instant.EPOCH).time
            whenever(weatherLocalService.getFavoriteCities()).thenReturn(listOf())
            whenever(dateRepo.nowDateTimeAsLong()).thenReturn(todayDate)
            // When
            weatherRepository.fetchWeatherForFavouriteCities()

            // Then
            verify(dateRepo, times(1)).nowDateTimeAsLong()
            verify(weatherLocalService, times(1)).getFavoriteCities()
            verifyNoMoreInteractions()
        }

    @Test
    fun `Given getFavouriteCitiesWeatherList called, Then it returns list of weather`(): Unit = runTest {
        // Given
        val localCitiesWithWeatherList = listOf(localWeather)
        val weatherList = listOf(weather)
        whenever(weatherLocalService.getFavouriteCitiesWeatherList()).thenReturn(localCitiesWithWeatherList)
        whenever(domainWeatherMapper.map(localCitiesWithWeatherList)).thenReturn(weatherList)

        // When
        val result = weatherRepository.getFavouriteCitiesWeatherList()

        // Then
        assertEquals(expected = weatherList, actual = result)
        verify(weatherLocalService, times(1)).getFavouriteCitiesWeatherList()
        verify(domainWeatherMapper, times(1)).map(localCitiesWithWeatherList)
        verifyNoMoreInteractions()
    }

    @Test
    fun `Given getFavouriteCitiesWeatherFlow called, Then it returns flow of weather list`(): Unit = runTest {
        // Given
        val localCitiesWithWeatherList = listOf(localWeather)
        val weatherList = listOf(weather)
        whenever(weatherLocalService.getFavouriteCitiesWeatherFlow()).thenReturn(flowOf(localCitiesWithWeatherList))
        whenever(domainWeatherMapper.map(localCitiesWithWeatherList)).thenReturn(weatherList)

        // When
        weatherRepository.getFavouriteCitiesWeatherFlow().test {
            val result = awaitItem()
            assertEquals(expected = weatherList, actual = result)
            cancelAndIgnoreRemainingEvents()
        }

        // Then
        verify(weatherLocalService, times(1)).getFavouriteCitiesWeatherFlow()
        verify(domainWeatherMapper, times(1)).map(localCitiesWithWeatherList)
        verifyNoMoreInteractions()
    }
}
