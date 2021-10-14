package com.wednesday.template.service_impl.base

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.wednesday.template.service_impl.room.AndroidTemplateDatabase

fun getRoomDatabase(applicationContext: Context): AndroidTemplateDatabase {
    return databaseBuilder(
        applicationContext,
        AndroidTemplateDatabase::class.java,
        "android_template_database"
    ).build()
}