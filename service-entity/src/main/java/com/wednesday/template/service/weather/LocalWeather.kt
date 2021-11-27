package com.wednesday.template.service.weather

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

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
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cityWoeid: Int,
    val title: String,
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val date: Date
) {
    constructor(
        cityWoeid: Int,
        title: String,
        temp: Double,
        minTemp: Double,
        maxTemp: Double,
        date: Date
    ) : this(0, cityWoeid, title, temp, minTemp, maxTemp, date)
}
