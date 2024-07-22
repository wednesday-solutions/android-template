package com.wednesday.template.repo.weather

import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun searchCities(searchTerm: String): List<City>

    fun getFavouriteCitiesFlow(): Flow<List<City>>

    suspend fun getFavouriteCitiesList(): List<City>

    suspend fun setCityAsFavourite(city: City)

    suspend fun removeCityAsFavourite(city: City)

    suspend fun fetchWeatherForFavouriteCities()

    suspend fun getFavouriteCitiesWeatherList(): List<Weather>

    fun getFavouriteCitiesWeatherFlow(): Flow<List<Weather>>
}
