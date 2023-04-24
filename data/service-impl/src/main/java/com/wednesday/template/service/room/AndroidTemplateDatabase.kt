package com.wednesday.template.service.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wednesday.template.service.DateConverter
import com.wednesday.template.service.openWeather.OpenWeatherLocalServiceImpl
import com.wednesday.template.service.openWeather.currentWeather.local.LocalCurrentWeather
import com.wednesday.template.service.openWeather.geoCoding.LocalLocation
import com.wednesday.template.service.room.migration.autoMigrationSpec.Version1to2MigrationSpec

@Database(
    entities = [LocalLocation::class, LocalCurrentWeather::class],
    version = 3,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = Version1to2MigrationSpec::class,
        ),
        AutoMigration(from = 2, to = 3),
    ],
    exportSchema = true,
)
@TypeConverters(DateConverter::class)
abstract class AndroidTemplateDatabase : RoomDatabase() {
    abstract fun databaseDao(): OpenWeatherLocalServiceImpl
}
