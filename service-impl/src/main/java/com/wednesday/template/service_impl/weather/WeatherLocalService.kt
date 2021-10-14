package com.wednesday.template.service_impl.weather

import androidx.room.*
import com.wednesday.template.service_entity.weather.LocalCity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherLocalService {

  @Query("Select * from favorite_cities")
  fun getObservableFavoriteCities(): Flow<List<LocalCity>>

  @Query("Select * from favorite_cities")
  suspend fun getFavoriteCities(): List<LocalCity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun markCityAsFavorite(city: LocalCity)

  @Delete
  suspend fun deleteFavoriteCity(city: LocalCity)
}
