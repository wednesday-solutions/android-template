package com.wednesday.template.service.base

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.wednesday.template.service.room.AndroidTemplateDatabase
import com.wednesday.template.service.room.MIGRATION_1_2

fun getRoomDatabase(applicationContext: Context): AndroidTemplateDatabase {
    return databaseBuilder(
        applicationContext,
        AndroidTemplateDatabase::class.java,
        "android_template_database"
    )
        .addMigrations(MIGRATION_1_2)
        .build()
}
