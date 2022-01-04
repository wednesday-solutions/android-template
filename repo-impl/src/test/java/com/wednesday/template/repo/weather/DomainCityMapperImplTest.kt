package com.wednesday.template.repo.weather

import com.wednesday.template.repo.weather.models.cityMappedFromLocalCity
import com.wednesday.template.repo.weather.models.cityMappedFromRemoteCity
import com.wednesday.template.repo.weather.models.localCity
import com.wednesday.template.repo.weather.models.remoteCity
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class DomainCityMapperImplTest {

    private lateinit var domainCityMapper: DomainCityMapperImpl

    @Before
    fun setup() {
        domainCityMapper = DomainCityMapperImpl()
    }

    @Test
    fun `Given LocalCity, When map called, City is returned with correct data`() {
        // Given
        val localCity = localCity

        // When
        val result = domainCityMapper.map(localCity)

        // Then
        assertEquals(expected = cityMappedFromLocalCity, actual = result)
    }

    @Test
    fun `Given RemoteCity, When map called, City is returned with correct data`() {
        // Given
        val localCity = remoteCity

        // When
        val result = domainCityMapper.mapRemoteCity(localCity)

        // Then
        assertEquals(expected = cityMappedFromRemoteCity, actual = result)
    }
}