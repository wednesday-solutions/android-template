package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.list.UIListItemBase
import com.wednesday.template.presentation.base.text.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIDayWeatherHeading(
    val text: UIText
) : UIListItemBase("UIDayWeatherHeading")
