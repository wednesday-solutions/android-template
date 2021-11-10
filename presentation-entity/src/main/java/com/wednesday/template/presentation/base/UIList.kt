package com.wednesday.template.presentation.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIList(
    val items: List<UIListItemBase> = listOf()
) : Parcelable
