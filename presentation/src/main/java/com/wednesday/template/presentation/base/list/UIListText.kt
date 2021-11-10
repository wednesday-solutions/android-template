package com.wednesday.template.presentation.base.list

import com.wednesday.template.presentation.base.list.UIListItemBase
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIListText(
    val text: String
) : UIListItemBase(id = "UIListText $text")
