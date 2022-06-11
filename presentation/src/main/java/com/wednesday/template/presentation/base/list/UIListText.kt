package com.wednesday.template.presentation.base.list

import kotlinx.parcelize.Parcelize

@Parcelize
data class UIListText(
    val text: String
) : UIListItemBase(id = "UIListText $text")
