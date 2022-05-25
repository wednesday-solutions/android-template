package com.wednesday.template.service.weather

import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import kotlinx.coroutines.flow.Flow

interface OpenWeatherLocalService {

    fun getFavoriteCitiesFlow(): Flow<List<LocalLocation>>

    suspend fun getFavoriteCities(): List<LocalLocation>

    suspend fun markCityAsFavorite(city: LocalLocation)

    suspend fun deleteFavoriteCity(city: LocalLocation)

    suspend fun upsertLocalCurrentWeather(weather: LocalCurrentWeather)

    suspend fun getLocalCurrentWeather(lat: Double, lon: Double): LocalCurrentWeather?

    suspend fun deleteLocalCurrentWeather(lat: Double, lon: Double)

    suspend fun getFavouriteCitiesWeatherList(): List<LocalCurrentWeather>

    fun getFavouriteCitiesWeatherFlow(): Flow<List<LocalCurrentWeather>>
}