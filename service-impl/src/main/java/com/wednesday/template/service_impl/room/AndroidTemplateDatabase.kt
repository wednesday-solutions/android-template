package com.wednesday.template.service_impl.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wednesday.template.service_entity.weather.LocalCity
import com.wednesday.template.service_impl.weather.WeatherLocalService

@Database(entities = [LocalCity::class], version = 1)
abstract class AndroidTemplateDatabase: RoomDatabase() {
  abstract fun databaseDao(): WeatherLocalService
}
