package com.wednesday.template.presentation.base.toolbar

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.wednesday.template.presentation.base.text.UIText
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIToolbar(
    val title: UIText,
    val hasBackButton: Boolean,
    @DrawableRes
    val menuIcon: Int?,
    val menuButtonEnabled: Boolean = true
) : Parcelable
