package com.wednesday.template.service.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherLocalServiceImpl : WeatherLocalService {

    @Query("Select * from favorite_cities")
    override fun getFavoriteCitiesFlow(): Flow<List<LocalCity>>

    @Query("Select * from favorite_cities")
    override suspend fun getFavoriteCities(): List<LocalCity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun markCityAsFavorite(city: LocalCity)

    @Delete
    override suspend fun deleteFavoriteCity(city: LocalCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addLocalWeather(weather: LocalWeather)

    @Query("delete from local_weather where cityWoeid=:woeid")
    override suspend fun deleteLocalWeather(woeid: Int)

    @Query("Select * from local_day_weather WHERE cityWoeid=:woeid")
    override suspend fun getLocalDayWeather(woeid: Int): List<LocalDayWeather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addLocalDayWeather(weatherList: LocalDayWeather)

    @Query("delete from local_day_weather where cityWoeid=:woeid")
    override suspend fun deleteLocalDayWeather(woeid: Int)

    @Transaction
    override suspend fun deleteCurrentAndAddNewWeatherData(
        woeid: Int,
        weather: LocalWeather,
        weatherList: List<LocalDayWeather>
    ) {
        deleteLocalWeather(woeid)
        deleteLocalDayWeather(woeid)

        addLocalWeather(weather)
        weatherList.forEach {
            addLocalDayWeather(it)
        }
    }

    @Query("Select * from local_weather WHERE cityWoeid=:woeid")
    override suspend fun getLocalWeather(woeid: Int): LocalWeather?

    @RewriteQueriesToDropUnusedColumns
    @Transaction
    @Query("Select * from favorite_cities INNER JOIN local_weather ON local_weather.cityWoeid=favorite_cities.woeid")
    override suspend fun getFavouriteCitiesWeatherList(): List<LocalCityWithWeather>

    @RewriteQueriesToDropUnusedColumns
    @Query("Select * from favorite_cities INNER JOIN local_weather ON local_weather.cityWoeid=favorite_cities.woeid")
    override fun getFavouriteCitiesWeatherFlow(): Flow<List<LocalCityWithWeather>>
}
