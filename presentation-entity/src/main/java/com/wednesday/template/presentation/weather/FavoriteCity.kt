package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteCity(
    val cityId: Int,
    val title: String,
    val displayTitle: UIText,
    val locationType: String,
    val displayLocationType: UIText,
    val latitude: String,
    val isFavourite: Boolean,
    val current_temp:String
): UIListItemBase(id = "UICity $cityId")
