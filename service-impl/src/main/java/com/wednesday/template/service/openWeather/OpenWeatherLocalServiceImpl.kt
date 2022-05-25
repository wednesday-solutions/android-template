package com.wednesday.template.service.openWeather

import androidx.room.Dao
import androidx.room.Query
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import com.wednesday.template.service.weather.OpenWeatherLocalService
import kotlinx.coroutines.flow.Flow

@Dao
interface OpenWeatherLocalServiceImpl : OpenWeatherLocalService {

    @Query("Select * from favorite_cities")
    override fun getFavoriteCitiesFlow(): Flow<List<LocalLocation>>

    override suspend fun getFavoriteCities(): List<LocalLocation>
    override suspend fun markCityAsFavorite(city: LocalLocation)

    override suspend fun deleteFavoriteCity(city: LocalLocation)
}