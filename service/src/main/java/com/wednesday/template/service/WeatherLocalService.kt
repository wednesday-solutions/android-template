package com.wednesday.template.service

import com.wednesday.template.service.weather.LocalCity
import kotlinx.coroutines.flow.Flow

interface WeatherLocalService {

    fun getFavoriteCitiesFlow(): Flow<List<LocalCity>>

    suspend fun getFavoriteCities(): List<LocalCity>

    suspend fun markCityAsFavorite(city: LocalCity)

    suspend fun deleteFavoriteCity(city: LocalCity)
}
