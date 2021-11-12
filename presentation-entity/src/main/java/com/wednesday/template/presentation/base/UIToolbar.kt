package com.wednesday.template.presentation.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIToolbar(
    val title: UIText,
    val hasBackButton: Boolean,
    val menuTitle: UIText?,
    val menuButtonEnabled: Boolean = true
) : Parcelable
