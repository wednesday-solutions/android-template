package com.wednesday.template.presentation.base

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIToolbar(
    val title: UIText,
    val hasBackButton: Boolean,
    @DrawableRes
    val menuIcon: Int? = null,
    val menuButtonEnabled: Boolean = true,
) : Parcelable
