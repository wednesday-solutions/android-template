package com.wednesday.template.presentation.base

import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Raw(val text: String) : UIText.Block

@Parcelize
data class Resource(@StringRes val resId: Int) : UIText.Block

@Parcelize
data class RawFormatted(
    val text: String,
    val arg1: String? = null,
    val arg2: String? = null,
    val arg3: String? = null
) : UIText.Block

@Parcelize
data class ResourceFormatted(
    @StringRes val resId: Int,
    val arg1: String? = null,
    val arg2: String? = null,
    val arg3: String? = null
) : UIText.Block
