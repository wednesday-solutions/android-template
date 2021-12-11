package com.wednesday.template.service.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "local_day_weather",
    foreignKeys = [
        ForeignKey(
            entity = LocalCity::class,
            parentColumns = arrayOf("woeid"),
            childColumns = arrayOf("cityWoeid"),
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class LocalDayWeather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(index = true)
    val cityWoeid: Int,
    val temp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val date: Date
)
