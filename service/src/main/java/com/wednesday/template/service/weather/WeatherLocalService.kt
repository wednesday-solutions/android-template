package com.wednesday.template.service.weather

import kotlinx.coroutines.flow.Flow

interface WeatherLocalService {

    fun getFavoriteCitiesFlow(): Flow<List<LocalCity>>

    suspend fun getFavoriteCities(): List<LocalCity>

    suspend fun markCityAsFavorite(city: LocalCity)

    suspend fun deleteFavoriteCity(city: LocalCity)
}
