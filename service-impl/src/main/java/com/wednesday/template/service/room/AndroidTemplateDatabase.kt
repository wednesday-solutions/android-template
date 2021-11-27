package com.wednesday.template.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wednesday.template.service.DateConverter
import com.wednesday.template.service.weather.LocalCity
import com.wednesday.template.service.weather.LocalWeather
import com.wednesday.template.service.weather.WeatherLocalServiceImpl

@Database(entities = [LocalCity::class, LocalWeather::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AndroidTemplateDatabase : RoomDatabase() {
    abstract fun databaseDao(): WeatherLocalServiceImpl
}
