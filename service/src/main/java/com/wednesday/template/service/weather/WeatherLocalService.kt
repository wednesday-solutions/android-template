package com.wednesday.template.service.weather

import kotlinx.coroutines.flow.Flow

interface WeatherLocalService {

    fun getFavoriteCitiesFlow(): Flow<List<LocalCity>>

    suspend fun getFavoriteCities(): List<LocalCity>

    suspend fun markCityAsFavorite(city: LocalCity)

    suspend fun deleteFavoriteCity(city: LocalCity)

    suspend fun addLocalWeather(weather: LocalWeather)

    suspend fun getLocalWeather(woeid: Int): LocalWeather?

    suspend fun getLocalDayWeather(woeid: Int): List<LocalDayWeather>

    suspend fun addLocalDayWeather(weatherList: LocalDayWeather)

    suspend fun deleteLocalDayWeather(woeid: Int)

    suspend fun deleteLocalWeather(woeid: Int)

    suspend fun deleteCurrentAndAddNewWeatherData(
        woeid: Int,
        weather: LocalWeather,
        weatherList: List<LocalDayWeather>
    )

    fun getFavouriteCitiesWeatherList(): List<LocalCityWithWeather>

    fun getFavouriteCitiesWeatherFlow(): Flow<List<LocalCityWithWeather>>
}
