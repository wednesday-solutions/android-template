package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.service.WeatherLocalService
import com.wednesday.template.service.weather.RemoteCity
import com.wednesday.template.service.weather.WeatherRemoteService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class WeatherRepositoryImplTest {

    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    private lateinit var weatherRemoteService: WeatherRemoteService
    private lateinit var weatherLocalService: WeatherLocalService
    private lateinit var domainCityMapper:DomainCityMapper
    private lateinit var localCityMapper: LocalCityMapper

    @Before
    fun setUp() {
        weatherRepository = mock()
        weatherRemoteService = mock()
        weatherLocalService = mock()
        domainCityMapper = mock()
        localCityMapper = mock()
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherRemoteService,weatherLocalService
            ,domainCityMapper,localCityMapper)
    }

    @Test
    fun `Given city name is empty, When searchCities, Then weatherRemoteService searchCity returns null`() =
        runBlocking {
            // Given
            val city = ""
            Mockito.`when`(weatherRemoteService.searchCities("")).thenReturn(listOf())

            // When
            val result = weatherRepositoryImpl.searchCities(city)

            // Then
            assertEquals(listOf<City>(),result)
        }

    @Test
    fun `Given city name, When searchCities, Then weatherRemoteService searchCity returns list of City`() =
        runBlocking {
            // Given
            val city = "Pune"
            whenever(weatherRemoteService.searchCities(city))
                .thenReturn(listOf(RemoteCity(1,"Pune","City")))
            whenever(domainCityMapper.mapRemoteCity(listOf(RemoteCity(1,"Pune","City"))))
                .thenReturn(listOf(City(1,"Pune","City")))

            // When
            val result = weatherRepositoryImpl.searchCities(city)

            // Then
            assertEquals(listOf(City(1,"Pune","City")),result)
        }

    @Test
    fun `Given city half name,When searchCities,Then weatherRemoteService searchCity returns list of City matching name`() =
        runBlocking {

            // Given
            val city = "De"
            val returnSearch = listOf(
                RemoteCity(1,"Delhi","City"),
                RemoteCity(2,"Dehradun","City"),
                RemoteCity(3,"Devprayag","City"))
            val expectedResult = listOf(
                City(1,"Delhi","City"),
                City(2,"Dehradun","City"),
                City(3,"Devprayag","City"))

            whenever(weatherRemoteService.searchCities(city))
                .thenReturn(returnSearch)
            whenever(domainCityMapper.mapRemoteCity(returnSearch))
                .thenReturn(expectedResult)

            // When
            val givenResult = weatherRepositoryImpl.searchCities(city)

            // Then
            assertEquals(expectedResult,givenResult)
        }



    @Test
    fun `Given search contains non alphabetic value, When searchCities, Then return empty list`() =
        runBlocking {
            // Given
            val city = "87878"
            val searchResult = listOf<RemoteCity>()
            val expectedResult = listOf<City>()

            whenever(weatherRemoteService.searchCities(city)).thenReturn(searchResult)
            whenever(domainCityMapper.mapRemoteCity(searchResult)).thenReturn(expectedResult)

            // When
            val givenResult = weatherRepositoryImpl.searchCities(city)

            // Then
            assertEquals(expectedResult,givenResult)
    }

    @Test
    fun `Given city named misspelled, When searchCities, Then return empty list`() =
        runBlocking{
            // Given
            val city = "Mumvai"
            val searchResult = listOf<RemoteCity>()
            val expectedResult = listOf<City>()

            whenever(weatherRemoteService.searchCities(city)).thenReturn(searchResult)
            whenever(domainCityMapper.mapRemoteCity(searchResult)).thenReturn(expectedResult)

            // When
            val givenResult = weatherRepositoryImpl.searchCities(city)

            //Then
            assertEquals(expectedResult,givenResult)
    }
}