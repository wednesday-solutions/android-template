package com.wednesday.template.presentation.base

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIToolbar(
    val title: UIText,
    val hasBackButton: Boolean,
    @DrawableRes
    val menuIcon: Int?,
    val menuButtonEnabled: Boolean = true
) : Parcelable
