package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIWeather(
    val lat: Double,
    val lon: Double,
    val title: UIText,
    val description: UIText,
    val currentTemp: UIText,
    val minMaxTemp: UIText,
    val feelsLike: UIText,
    val iconUrl: String,
) : UIListItemBase(id = "UICity $lat $lon")
