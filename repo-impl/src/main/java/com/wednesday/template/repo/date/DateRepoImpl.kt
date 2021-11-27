package com.wednesday.template.repo.date

import android.annotation.SuppressLint
import com.wednesday.template.domain.date.Date
import com.wednesday.template.domain.date.DateTime
import com.wednesday.template.domain.date.JavaDate
import com.wednesday.template.domain.date.Time
import com.wednesday.template.domain.date.TimePeriod
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.Comparator

@SuppressLint("SimpleDateFormat")
class DateRepoImpl : DateRepo {

    override val timeComparator = Comparator { t1: Time, t2: Time ->
        compareTimes(t1, t2)
    }

    override fun todayDate() = mapDate(JavaDate())

    override fun nowDateTime() = mapDateTime(JavaDate())

    override fun nowTime() = mapTime(JavaDate())

    override fun addDays(date: Date, days: Int): Date {
        val javaDate = mapDate(date)
        return mapDate(addDays(javaDate, days))
    }

    private fun addDays(date: JavaDate, days: Int): JavaDate {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, days)
        return calendar.time
    }

    override fun format(date: Date, format: String): String {
        val javaDate = mapDate(date)
        return SimpleDateFormat(format).format(javaDate)
    }

    override fun format(dateTime: DateTime, format: String): String {
        val javaDate = mapDateTime(dateTime)
        return SimpleDateFormat(format).format(javaDate)
    }

    override fun format(time: Time, format: String): String {
        val javaDate = mapTime(time)
        return SimpleDateFormat(format).format(javaDate)
    }

    override fun convertToLong(date: Date) = mapDate(date).time

    override fun convertToLong(dateTime: DateTime) = mapDateTime(dateTime).time

    override fun convertToDate(timeInMillis: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return Date(
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR)
        )
    }

    override fun convertToDateTime(timeInMillis: Long): DateTime {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return DateTime(
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR),
            hourOfDay = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            second = calendar.get(Calendar.SECOND),
            millisecond = calendar.get(Calendar.MILLISECOND)
        )
    }

    override fun compareDates(source: Date, with: Date): Int {
        val javaSource = mapDate(source)
        val withSource = mapDate(with)
        return javaSource.compareTo(withSource)
    }

    override fun compareDates(source: JavaDate, with: JavaDate): Int {
        return source.compareTo(with)
    }

    override fun compareTimes(source: Time, with: Time): Int {
        val javaSource = mapTime(source)
        val withSource = mapTime(with)
        return javaSource.compareTo(withSource)
    }

    override fun getTimePeriodRangeMinFromTime(time: Time): Time {
        return when (mapTimePeriod(time)) {
            TimePeriod.MORNING -> hourTime(0)
            TimePeriod.AFTERNOON -> hourTime(11)
            TimePeriod.EVENING -> hourTime(17)
            TimePeriod.NIGHT -> hourTime(21)
        }
    }

    override fun getTimePeriodRangeMaxFromTime(time: Time): Time {
        return when (mapTimePeriod(time)) {
            TimePeriod.MORNING -> hourMinuteTime(10, 59)
            TimePeriod.AFTERNOON -> hourMinuteTime(16, 59)
            TimePeriod.EVENING -> hourMinuteTime(20, 59)
            TimePeriod.NIGHT -> hourMinuteTime(23, 59)
        }
    }

    override fun getDefaultTimeForTimePeriod(timePeriod: TimePeriod) = when (timePeriod) {
        TimePeriod.MORNING -> hourTime(9)
        TimePeriod.AFTERNOON -> hourTime(13)
        TimePeriod.EVENING -> hourTime(18)
        TimePeriod.NIGHT -> hourTime(22)
    }

    override fun mapDate(date: JavaDate): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return Date(
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR)
        )
    }

    override fun mapDate(dateTime: DateTime): Date {
        return Date(
            dayOfMonth = dateTime.dayOfMonth,
            month = dateTime.month,
            year = dateTime.year
        )
    }

    override fun mapDateTime(date: JavaDate): DateTime {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return DateTime(
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH),
            month = calendar.get(Calendar.MONTH),
            year = calendar.get(Calendar.YEAR),
            hourOfDay = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            second = calendar.get(Calendar.SECOND),
            millisecond = calendar.get(Calendar.MILLISECOND)
        )
    }

    override fun mapDateTime(date: Date, time: Time): DateTime {
        return DateTime(
            dayOfMonth = date.dayOfMonth,
            month = date.month,
            year = date.year,
            hourOfDay = time.hourOfDay,
            minute = time.minute,
            second = time.second,
            millisecond = time.millisecond
        )
    }

    override fun mapHoursMinutesString(time: String): Time {
        val hoursMinutes = time.split(":")
        return Time(
            hourOfDay = hoursMinutes[0].toInt(),
            minute = hoursMinutes[1].toInt(),
            second = 0,
            millisecond = 0
        )
    }

    override fun mapTimePeriod(time: Time): TimePeriod {
        val am0 = hourTime(0)
        val am11 = hourTime(11)
        val pm5 = hourTime(17)
        val pm9 = hourTime(21)
        return when {
            time >= am0 && time < am11 -> TimePeriod.MORNING
            time >= am11 && time < pm5 -> TimePeriod.AFTERNOON
            time >= pm5 && time < pm9 -> TimePeriod.EVENING
            else -> TimePeriod.NIGHT
        }
    }

    private fun hourTime(hour: Int) = Time(
        hourOfDay = hour,
        minute = 0,
        second = 0,
        millisecond = 0
    )

    private fun hourMinuteTime(hour: Int, minute: Int) = Time(
        hourOfDay = hour,
        minute = minute,
        second = 0,
        millisecond = 0
    )

    override fun mapDate(date: Date): JavaDate = date.run {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.time
    }

    override fun mapDateTime(dateTime: DateTime): JavaDate = dateTime.run {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, millisecond)
        calendar.time
    }

    override fun mapTime(time: Time): JavaDate = time.run {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, millisecond)
        calendar.time
    }

    override fun mapTime(date: JavaDate): Time {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return Time(
            hourOfDay = calendar.get(Calendar.HOUR_OF_DAY),
            minute = calendar.get(Calendar.MINUTE),
            second = calendar.get(Calendar.SECOND),
            millisecond = calendar.get(Calendar.MILLISECOND)
        )
    }

    override fun datesInBetween(start: Date, end: Date): List<Date> {
        val startJavaDate = mapDate(start)
        val endJavaDate = mapDate(end)
        if (startJavaDate >= endJavaDate) return emptyList()
        var tempDate = startJavaDate
        val dates = mutableListOf<JavaDate>()
        while (tempDate < endJavaDate) {
            dates.add(tempDate)
            tempDate = addDays(tempDate, days = 1)
        }
        return dates.map { mapDate(it) }
    }

    override fun parseJavaDate(date: String, format: String): JavaDate {
        return SimpleDateFormat(format).parse(date) as JavaDate
    }

    private operator fun Time.compareTo(other: Time): Int {
        return timeComparator.compare(this, other)
    }
}
