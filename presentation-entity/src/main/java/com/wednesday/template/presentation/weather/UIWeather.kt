package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIWeather(
    val cityId: Int,
    val title: UIText,
    val currentTemp: UIText,
    val minMaxTemp: UIText
) : UIListItemBase(id = "UICity $cityId")
