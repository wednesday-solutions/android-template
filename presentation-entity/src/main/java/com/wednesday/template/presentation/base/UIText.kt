package com.wednesday.template.presentation.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class UIText(
    val text: String
) : Parcelable
