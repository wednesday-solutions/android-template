package com.wednesday.template.presentation.base

import android.os.Parcelable
import com.wednesday.template.presentation_entity.R
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class UIText(
    val resId: Int = R.string.empty_string
) : Parcelable
