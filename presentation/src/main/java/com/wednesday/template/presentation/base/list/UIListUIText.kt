package com.wednesday.template.presentation.base.list

import com.wednesday.template.presentation.base.UIListItemBase
import com.wednesday.template.presentation.base.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIListUIText(
    val text: UIText
) : UIListItemBase(id = "UIListUIText $text")
