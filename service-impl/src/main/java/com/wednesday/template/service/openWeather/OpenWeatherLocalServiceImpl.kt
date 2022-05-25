package com.wednesday.template.service.openWeather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import com.wednesday.template.service.weather.OpenWeatherLocalService
import kotlinx.coroutines.flow.Flow

@Dao
interface OpenWeatherLocalServiceImpl : OpenWeatherLocalService {

    @Query("Select * from favorite_locations")
    override fun getFavoriteCitiesFlow(): Flow<List<LocalLocation>>

    @Query("Select * from favorite_locations")
    override suspend fun getFavoriteCities(): List<LocalLocation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun markCityAsFavorite(city: LocalLocation)

    @Delete
    override suspend fun deleteFavoriteCity(city: LocalLocation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun upsertLocalCurrentWeather(weather: LocalCurrentWeather)

    @Query("Select * from current_weather where coord_lat=:lat and coord_lon=:lon")
    override suspend fun getLocalCurrentWeather(lat: Double, lon: Double): LocalCurrentWeather?

    @Query("Delete from current_weather where coord_lat=:lat and coord_lon=:lon")
    override suspend fun deleteLocalCurrentWeather(lat: Double, lon: Double)

    @Query("Select * from current_weather")
    override suspend fun getFavouriteCitiesWeatherList(): List<LocalCurrentWeather>

    @Query("Select * from current_weather")
    override fun getFavouriteCitiesWeatherFlow(): Flow<List<LocalCurrentWeather>>
}