package com.wednesday.template.repo.weather


 import app.cash.turbine.test
 import com.wednesday.template.repo.date.DateRepo
 import com.wednesday.template.repo.weather.models.cityMappedFromLocalCity
 import com.wednesday.template.repo.weather.models.cityMappedFromRemoteCity
 import com.wednesday.template.repo.weather.models.localCity
 import com.wednesday.template.repo.weather.models.remoteCity
 import com.wednesday.template.service.weather.OpenWeatherLocalService
 import com.wednesday.template.service.weather.OpenWeatherRemoteService
 import kotlinx.coroutines.flow.flowOf
 import kotlinx.coroutines.runBlocking
 import org.junit.Before
 import org.junit.Test
 import org.mockito.kotlin.mock
 import org.mockito.kotlin.same
 import org.mockito.kotlin.times
 import org.mockito.kotlin.verify
 import org.mockito.kotlin.verifyNoMoreInteractions
 import org.mockito.kotlin.whenever
 import kotlin.test.assertEquals
 import kotlin.time.ExperimentalTime

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
            dateRepo
        )
    }

    private fun verifyNoMoreInteractions() {
        verifyNoMoreInteractions(
            weatherRemoteService,
            weatherLocalService,
            domainCityMapper,
            localCityMapper,
            localWeatherMapper,
            domainWeatherMapper,
            dateRepo
        )
    }

    @Test
    fun `Given a search string, When searchCities is called, Then list of cities is returned`(): Unit =
        runBlocking {
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
        runBlocking {
            // Given
            val localCities = listOf(localCity)
            val cities = listOf(cityMappedFromLocalCity)
            whenever(weatherLocalService.getFavoriteCitiesFlow()).thenReturn(
                flowOf(
                    localCities,
                    localCities
                )
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
        runBlocking {
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
    fun `Given setCityAsFavourite called, Then it marks the city favourite`(): Unit = runBlocking {
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
        runBlocking {
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

//    @Test
//    fun `Given fetchWeatherForFavouriteCities, When day weather is empty, Then weather data is refreshed`(): Unit =
//        runBlocking {
//            // Given
//            val todayDate = Date(1, 1, 1970)
//            val woeid = localCity.woeid
//            val localWeather = localWeather
//            val localCities = listOf(localCity)
//            val localDayWeatherList = listOf(localDayWeather)
//            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
//            whenever(dateRepo.todayDate()).thenReturn(todayDate)
//            whenever(weatherLocalService.getLocalDayWeather(woeid)).thenReturn(listOf())
//            whenever(weatherRemoteService.weatherForCity(woeid)).thenReturn(remoteWeather)
//            whenever(localWeatherMapper.map(remoteWeather, woeid)).thenReturn(localWeather)
//            whenever(
//                localDayWeatherMapper.map(
//                    remoteWeather,
//                    woeid
//                )
//            ).thenReturn(localDayWeatherList)
//
//            // When
//            weatherRepository.fetchWeatherForFavouriteCities()
//
//            // Then
//            verify(dateRepo, times(1)).todayDate()
//            verify(weatherLocalService, times(1)).getFavoriteCities()
//            verify(weatherLocalService, times(1)).getLocalDayWeather(woeid)
//            verify(weatherRemoteService, times(1)).weatherForCity(woeid)
//            verify(localWeatherMapper, times(1)).map(remoteWeather, woeid)
//            verify(localDayWeatherMapper, times(1)).map(remoteWeather, woeid)
//            verify(weatherLocalService, times(1)).deleteCurrentAndAddNewWeatherData(
//                woeid,
//                localWeather,
//                localDayWeatherList
//            )
//            verifyNoMoreInteractions()
//        }
//
//    @Test
//    fun `Given fetchWeatherForFavouriteCities, When weather list is stale, Then weather data is refreshed`(): Unit =
//        runBlocking {
//            // Given
//            val todayDate = Date(1, 1, 9999)
//            val woeid = localCity.woeid
//            val localWeather = localWeather
//            val localCities = listOf(localCity)
//            val localDayWeatherList = listOf(localDayWeather)
//            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
//            whenever(dateRepo.todayDate()).thenReturn(todayDate)
//            whenever(dateRepo.mapDate(localDayWeather.date)).thenReturn(Date(1, 1, 1970))
//            whenever(weatherLocalService.getLocalDayWeather(woeid)).thenReturn(localDayWeatherList)
//            whenever(weatherRemoteService.weatherForCity(woeid)).thenReturn(remoteWeather)
//            whenever(localWeatherMapper.map(remoteWeather, woeid)).thenReturn(localWeather)
//            whenever(
//                localDayWeatherMapper.map(
//                    remoteWeather,
//                    woeid
//                )
//            ).thenReturn(localDayWeatherList)
//
//            // When
//            weatherRepository.fetchWeatherForFavouriteCities()
//
//            // Then
//            verify(dateRepo, times(1)).todayDate()
//            verify(dateRepo, times(1)).mapDate(localDayWeather.date)
//            verify(weatherLocalService, times(1)).getFavoriteCities()
//            verify(weatherLocalService, times(1)).getLocalDayWeather(woeid)
//            verify(weatherRemoteService, times(1)).weatherForCity(woeid)
//            verify(localWeatherMapper, times(1)).map(remoteWeather, woeid)
//            verify(localDayWeatherMapper, times(1)).map(remoteWeather, woeid)
//            verify(weatherLocalService, times(1)).deleteCurrentAndAddNewWeatherData(
//                woeid,
//                localWeather,
//                localDayWeatherList
//            )
//            verifyNoMoreInteractions()
//        }
//
//    @Test
//    fun `Given fetchWeatherForFavouriteCities, When weather list is not stale and day weather is not empty, Then weather data is not refreshed`(): Unit =
//        runBlocking {
//            // Given
//            val todayDate = Date(1, 1, 1970)
//            val woeid = localCity.woeid
//            val localCities = listOf(localCity)
//            val localDayWeatherList = listOf(localDayWeather)
//            whenever(weatherLocalService.getFavoriteCities()).thenReturn(localCities)
//            whenever(dateRepo.todayDate()).thenReturn(todayDate)
//            whenever(dateRepo.mapDate(localDayWeather.date)).thenReturn(todayDate)
//            whenever(weatherLocalService.getLocalDayWeather(woeid)).thenReturn(localDayWeatherList)
//
//            // When
//            weatherRepository.fetchWeatherForFavouriteCities()
//
//            // Then
//            verify(dateRepo, times(1)).todayDate()
//            verify(dateRepo, times(1)).mapDate(localDayWeather.date)
//            verify(weatherLocalService, times(1)).getFavoriteCities()
//            verify(weatherLocalService, times(1)).getLocalDayWeather(woeid)
//            verifyNoMoreInteractions()
//        }
//
//    @Test
//    fun `Given fetchWeatherForFavouriteCities, When favourite cities is empty, Then nothing happens`(): Unit =
//        runBlocking {
//            // Given
//            val todayDate = Date(1, 1, 1970)
//            whenever(weatherLocalService.getFavoriteCities()).thenReturn(listOf())
//            whenever(dateRepo.todayDate()).thenReturn(todayDate)
//            // When
//            weatherRepository.fetchWeatherForFavouriteCities()
//
//            // Then
//            verify(dateRepo, times(1)).todayDate()
//            verify(weatherLocalService, times(1)).getFavoriteCities()
//            verifyNoMoreInteractions()
//        }
//
//    @Test
//    fun `Given getFavouriteCitiesWeatherList called, Then it returns list of weather`(): Unit = runBlocking {
//        // Given
//        val localCitiesWithWeatherList = listOf(localCityWithWeather)
//        val weatherList = listOf(weatherMappedFromLocalCityWithWeather)
//        whenever(weatherLocalService.getFavouriteCitiesWeatherList()).thenReturn(localCitiesWithWeatherList)
//        whenever(domainWeatherMapper.map(localCitiesWithWeatherList)).thenReturn(weatherList)
//
//        // When
//        val result = weatherRepository.getFavouriteCitiesWeatherList()
//
//        // Then
//        assertEquals(expected = weatherList, actual = result)
//        verify(weatherLocalService, times(1)).getFavouriteCitiesWeatherList()
//        verify(domainWeatherMapper, times(1)).map(localCitiesWithWeatherList)
//        verifyNoMoreInteractions()
//    }
//
//    @Test
//    fun `Given getFavouriteCitiesWeatherFlow called, Then it returns flow of weather list`(): Unit = runBlocking {
//        // Given
//        val localCitiesWithWeatherList = listOf(localCityWithWeather)
//        val weatherList = listOf(weatherMappedFromLocalCityWithWeather)
//        whenever(weatherLocalService.getFavouriteCitiesWeatherFlow()).thenReturn(flowOf(localCitiesWithWeatherList))
//        whenever(domainWeatherMapper.map(localCitiesWithWeatherList)).thenReturn(weatherList)
//
//        // When
//        weatherRepository.getFavouriteCitiesWeatherFlow().test {
//            val result = awaitItem()
//            assertEquals(expected = weatherList, actual = result)
//            cancelAndIgnoreRemainingEvents()
//        }
//
//        // Then
//        verify(weatherLocalService, times(1)).getFavouriteCitiesWeatherFlow()
//        verify(domainWeatherMapper, times(1)).map(localCitiesWithWeatherList)
//        verifyNoMoreInteractions()
//    }
 }
