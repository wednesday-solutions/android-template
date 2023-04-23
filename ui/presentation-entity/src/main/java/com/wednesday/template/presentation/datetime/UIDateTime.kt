package com.wednesday.template.presentation.datetime

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIDateTime(
    val dayOfMonth: Int, // from 1 to 31
    val month: Int, // from 0 to 11
    val year: Int,
    val hourOfDay: Int, // from 0 to 23
    val minute: Int,
    val second: Int,
    val millisecond: Int,
    val timeAsLong: Long,
) : Parcelable
