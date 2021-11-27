package com.wednesday.template.domain.date

data class Date(
    val dayOfMonth: Int, // from 1 to 31
    val month: Int, // from 0 to 11
    val year: Int
) {

    companion object {
        val MAX = Date(dayOfMonth = 31, month = 11, year = 9999)
        val MIN = Date(dayOfMonth = 1, month = 0, year = 1970)
    }
}