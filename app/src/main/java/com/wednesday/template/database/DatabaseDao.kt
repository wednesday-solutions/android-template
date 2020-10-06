package com.wednesday.template.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wednesday.template.model.City

@Dao
interface DatabaseDao {
  @Query("Select * from favorite_cities")
  fun getObservableFavoriteCities(): LiveData<List<City>>

  @Query("Select * from favorite_cities")
  suspend fun getFavoriteCities(): List<City>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun markCityAsFavorite(city: City)

  @Delete
  suspend fun deleteFavoriteCity(city: City)
}
