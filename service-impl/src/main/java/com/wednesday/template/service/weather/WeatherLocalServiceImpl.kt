package com.wednesday.template.service.weather

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}
