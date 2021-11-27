package com.wednesday.template.domain.date

data class DateTime(
    val dayOfMonth: Int, // from 1 to 31
    val month: Int, // from 0 to 11
    val year: Int,
    val hourOfDay: Int, //from 0 to 23
    val minute: Int, //from 0 to 59
    val second: Int, //from 0 to 59
    val millisecond: Int //from 0 to 999
)