package com.wednesday.template.domain.weather

data class City(
    val id: Int,
    val title: String,
    val country: String,
    val state: String?,
    val lat: Double,
    val lon: Double
)
