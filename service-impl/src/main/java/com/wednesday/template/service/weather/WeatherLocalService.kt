package com.wednesday.template.service.weather

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherLocalService {

  @Query("Select * from favorite_cities")
  fun getFavoriteCitiesFlow(): Flow<List<LocalCity>>

  @Query("Select * from favorite_cities")
  suspend fun getFavoriteCities(): List<LocalCity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun markCityAsFavorite(city: LocalCity)

  @Delete
  suspend fun deleteFavoriteCity(city: LocalCity)
}
