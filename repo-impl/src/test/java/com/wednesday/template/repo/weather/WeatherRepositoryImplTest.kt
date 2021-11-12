package com.wednesday.template.repo.weather

import com.wednesday.template.service.WeatherLocalService
import com.wednesday.template.service.weather.RemoteCity
import com.wednesday.template.service.weather.WeatherRemoteService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.kotlin.mock

class WeatherRepositoryImplTest {

    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    private lateinit var weatherRemoteService: WeatherRemoteService
    private lateinit var weatherLocalService: WeatherLocalService
    private lateinit var domainCityMapper:DomainCityMapper
    private lateinit var localCityMapper: LocalCityMapper

    @Before
    fun setUp() {
        weatherRemoteService = mock()
        weatherLocalService = mock()
        domainCityMapper = mock()
        localCityMapper = mock()
        weatherRepositoryImpl = WeatherRepositoryImpl(weatherRemoteService,weatherLocalService
            ,domainCityMapper,localCityMapper)
    }

    @Test
    fun `Search City By Empty String Returns Empty List`()= runBlocking{
        Mockito.`when`(weatherRemoteService.searchCities("")).thenReturn(listOf())
        assertEquals(listOf<RemoteCity>(),weatherRepositoryImpl.searchCities(""))
    }

    @Test
    fun `Search City By Full Name Returns The City`() = runBlocking{
        Mockito.`when`(weatherRemoteService.searchCities("Pune"))
            .thenReturn(listOf(RemoteCity(1,"Pune","City")))
        assertEquals(listOf(RemoteCity(1,"Pune","City")),weatherRemoteService.searchCities("Pune"))
    }

    @Test
    fun `Search City By Half Name Should Return List Of Cites Starting From Given Characters`() =
        runBlocking{
         Mockito.`when`(weatherRemoteService.searchCities("De"))
             .thenReturn(listOf(
                 RemoteCity(1,"Delhi","City"),
                 RemoteCity(2,"Dehradun","City"),
                 RemoteCity(3,"Devprayag","City")))

            assertEquals(listOf(
                RemoteCity(1,"Delhi","City"),
                RemoteCity(2,"Dehradun","City"),
                RemoteCity(3,"Devprayag","City")),weatherRemoteService.searchCities("De"))
        }

    @Test
    fun `Search City Without Alphabets Returns Empty List`() = runBlocking {
        Mockito.`when`(weatherRemoteService.searchCities("87878")).thenReturn(listOf())
        assertEquals(listOf<RemoteCity>(),weatherRemoteService.searchCities("87878"))
    }

    @Test
    fun `Search City With Misspelled Alphabets Returns Empty List`() = runBlocking{
        Mockito.`when`(weatherRemoteService.searchCities("Mumvai")).thenReturn(listOf())
        assertEquals(listOf<RemoteCity>(),weatherRemoteService.searchCities("Mumvai"))
    }

}