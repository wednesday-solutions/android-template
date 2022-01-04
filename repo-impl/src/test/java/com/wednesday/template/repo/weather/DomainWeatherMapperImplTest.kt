package com.wednesday.template.repo.weather

import com.wednesday.template.repo.weather.models.dayWeatherMapperFromLocalDayWeather
import com.wednesday.template.repo.weather.models.localCityWithWeather
import com.wednesday.template.repo.weather.models.localDayWeather
import com.wednesday.template.repo.weather.models.weatherMappedFromLocalCityWithWeather
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class DomainWeatherMapperImplTest {

    private lateinit var domainDayWeatherMapper: DomainDayWeatherMapper
    private lateinit var domainWeatherMapper: DomainWeatherMapperImpl

    @Before
    fun setUp() {
        domainDayWeatherMapper = mock()
        domainWeatherMapper = DomainWeatherMapperImpl(domainDayWeatherMapper)
    }

    @Test
    fun `Given LocalCityWithWeather, When map is called, Then Weather is returned with correct data`() {
        // Given
        val localCityWithWeather = localCityWithWeather
        val localDayWeather = localDayWeather
        whenever(domainDayWeatherMapper.map(listOf(localDayWeather))).thenReturn(
            listOf(dayWeatherMapperFromLocalDayWeather)
        )

        // When
        val result = domainWeatherMapper.map(localCityWithWeather)

        // Then
        assertEquals(expected = weatherMappedFromLocalCityWithWeather, actual = result)
        verify(domainDayWeatherMapper, times(1)).map(listOf(localDayWeather))
    }
}
