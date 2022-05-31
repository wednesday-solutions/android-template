package com.wednesday.template.repo.weather

import com.wednesday.template.repo.weather.models.localWeather
import com.wednesday.template.repo.weather.models.weather
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class DomainWeatherMapperImplTest {

    private lateinit var domainWeatherMapper: DomainWeatherMapperImpl

    @Before
    fun setUp() {
        domainWeatherMapper = DomainWeatherMapperImpl()
    }

    @Test
    fun `Given LocalCityWithWeather, When map is called, Then Weather is returned with correct data`() {
        // Given
        val currentWeather = localWeather

        // When
        val result = domainWeatherMapper.map(currentWeather)

        // Then
        assertEquals(expected = weather, actual = result)
    }
}
