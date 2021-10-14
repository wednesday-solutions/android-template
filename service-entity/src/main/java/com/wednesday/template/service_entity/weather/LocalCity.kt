package com.wednesday.template.service_entity.weather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cities")
data class LocalCity(
    @PrimaryKey
    val woeid: Int,
    val title: String,
    val locationType: String
)