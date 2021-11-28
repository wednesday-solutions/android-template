package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIDayWeather(
    val cityId: Int,
    val currentTemp: UIText,
    val minMaxTemp: UIText,
    val date: UIText
): UIListItemBase(id = "UICity $cityId $date")

