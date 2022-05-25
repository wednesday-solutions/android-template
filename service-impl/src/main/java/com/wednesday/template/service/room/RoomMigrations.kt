package com.wednesday.template.service.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE favorite_cities")
        database.execSQL("DROP TABLE local_weather")
        database.execSQL("DROP TABLE local_day_weather")
        database.execSQL("CREATE TABLE `favorite_locations` (`country` TEXT NOT NULL, `lat` DOUBLE NOT NULL, `lon` DOUBLE NOT NULL, `name` TEXT NOT NULL, PRIMARY KEY (`lat`, `lon`))")
    }
}