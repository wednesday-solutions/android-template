package com.wednesday.template.service.weather

import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import kotlinx.coroutines.flow.Flow

interface OpenWeatherLocalService {

    fun getFavoriteCitiesFlow(): Flow<List<LocalLocation>>

    suspend fun getFavoriteCities(): List<LocalLocation>

    suspend fun markCityAsFavorite(city: LocalLocation)

    suspend fun deleteFavoriteCity(city: LocalLocation)

//        suspend fun addLocalWeather(weather: LocalWeather)
//
//        suspend fun getLocalWeather(woeid: Int): LocalWeather?
//
//        suspend fun getLocalDayWeather(woeid: Int): List<LocalDayWeather>
//
//        suspend fun addLocalDayWeather(weatherList: LocalDayWeather)
//
//        suspend fun deleteLocalDayWeather(woeid: Int)
//
//        suspend fun deleteLocalWeather(woeid: Int)
//
//        suspend fun deleteCurrentAndAddNewWeatherData(
//            woeid: Int,
//            weather: LocalWeather,
//            weatherList: List<LocalDayWeather>
//        )
//
//        suspend fun getFavouriteCitiesWeatherList(): List<LocalCityWithWeather>
//
//        fun getFavouriteCitiesWeatherFlow(): Flow<List<LocalCityWithWeather>>
}