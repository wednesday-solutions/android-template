package com.wednesday.template.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.wednesday.template.presentation.base.UIListItemBase
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
@Immutable
value class UIList(
    val items: List<UIListItemBase> = listOf(),
) : Parcelable {
    constructor(vararg items: UIListItemBase) : this(listOf(*items))
}

