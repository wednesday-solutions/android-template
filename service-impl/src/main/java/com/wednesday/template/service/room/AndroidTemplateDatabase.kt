package com.wednesday.template.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wednesday.template.service.weather.LocalCity
import com.wednesday.template.service.weather.WeatherLocalServiceImpl

@Database(entities = [LocalCity::class], version = 1)
abstract class AndroidTemplateDatabase: RoomDatabase() {
  abstract fun databaseDao(): WeatherLocalServiceImpl
}
