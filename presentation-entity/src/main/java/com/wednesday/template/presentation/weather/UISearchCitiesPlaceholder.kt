package com.wednesday.template.presentation.weather

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UISearchCitiesPlaceholder(
    val text: UIText
): UIListItemBase("UISearchCitiesPlaceholder")