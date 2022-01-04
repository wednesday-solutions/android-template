package com.wednesday.template.repo.weather

import com.wednesday.template.domain.date.Date
import com.wednesday.template.repo.date.DateRepo
import com.wednesday.template.repo.weather.models.dayWeatherMapperFromLocalDayWeather
import com.wednesday.template.repo.weather.models.localDayWeather
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class DomainDayWeatherMapperImplTest {

    private lateinit var dateRepo: DateRepo
    private lateinit var domainDayWeatherMapper: DomainDayWeatherMapperImpl

    @Before
    fun setUp() {
        dateRepo = mock()
        domainDayWeatherMapper = DomainDayWeatherMapperImpl(dateRepo)
    }

    @Test
    fun `Given LocalDayWeather, When map is called, Then DayWeather is returned with correct data`() {
        // Given
        val localDayWeather = localDayWeather
        whenever(dateRepo.mapDate(localDayWeather.date)).thenReturn(Date(1, 1, 1970))

        // When
        val result = domainDayWeatherMapper.map(localDayWeather)

        // Then
        assertEquals(expected = dayWeatherMapperFromLocalDayWeather, actual = result)
        verify(dateRepo, times(1)).mapDate(localDayWeather.date)
    }
}
