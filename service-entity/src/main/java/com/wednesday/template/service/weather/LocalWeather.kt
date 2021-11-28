package com.wednesday.template.service.weather

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "local_weather",
    foreignKeys = [
        ForeignKey(
            entity = LocalCity::class,
            parentColumns = arrayOf("woeid"),
            childColumns = arrayOf("cityWoeid"),
            onDelete = CASCADE
        )
    ],
)
data class LocalWeather(
    @PrimaryKey
    val cityWoeid: Int,
    val title: String,
)
