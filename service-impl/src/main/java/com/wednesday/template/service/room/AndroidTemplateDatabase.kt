package com.wednesday.template.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wednesday.template.service.DateConverter
import com.wednesday.template.service.openWeather.OpenWeatherLocalServiceImpl
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation

@Database(entities = [LocalLocation::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class AndroidTemplateDatabase : RoomDatabase() {
    abstract fun databaseDao(): OpenWeatherLocalServiceImpl
}
