package com.wednesday.template.repo.date

import com.wednesday.template.domain.date.Date
import com.wednesday.template.domain.date.DateTime
import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.domain.date.Time
import com.wednesday.template.domain.date.TimePeriod

interface DateRepo {

    val timeComparator: Comparator<Time>

    fun todayDate(): Date

    fun nowDateTime(): DateTime

    fun nowTime(): Time

    fun addDays(date: Date, days: Int): Date

    fun format(date: Date, format: String): String

    fun format(dateTime: DateTime, format: String): String

    fun format(time: Time, format: String): String

    fun convertToLong(date: Date): Long

    fun convertToLong(dateTime: DateTime): Long

    fun nowDateTimeAsLong(): Long

    fun convertToDate(timeInMillis: Long): Date

    fun convertToDateTime(timeInMillis: Long): DateTime

    fun compareDates(source: Date, with: Date): Int

    fun compareDates(source: JavaDate, with: JavaDate): Int

    fun compareTimes(source: Time, with: Time): Int

    fun getTimePeriodRangeMinFromTime(time: Time): Time

    fun getTimePeriodRangeMaxFromTime(time: Time): Time

    fun getDefaultTimeForTimePeriod(timePeriod: TimePeriod): Time

    fun mapDate(date: JavaDate): Date

    fun mapDate(dateTime: DateTime): Date

    fun mapDateTime(date: JavaDate): DateTime

    fun mapDateTime(date: Date, time: Time): DateTime

    fun mapHoursMinutesString(time: String): Time

    fun mapTimePeriod(time: Time): TimePeriod

    fun mapDate(date: Date): JavaDate

    fun mapDateTime(dateTime: DateTime): JavaDate

    fun mapTime(time: Time): JavaDate

    fun mapTime(date: JavaDate): Time

    fun datesInBetween(start: Date, end: Date): List<Date> // end is exclusive

    fun parseJavaDate(date: String, format: String): JavaDate
}
