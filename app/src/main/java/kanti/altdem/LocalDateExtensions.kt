package kanti.altdem

import java.time.LocalDate
import java.time.chrono.IsoChronology

fun LocalDate.toEndDate(): LocalDate = plusMonths(21)

fun LocalDate.toRuString(): String {
	val sb = StringBuilder()
	sb.append(dayOfMonth).append('.')
	sb.append(monthValue).append('.')
	sb.append(year)
	return sb.toString()
}

val LocalDate.allDays: Int
	get() {
		val year = year - 2000
		var days = 0
		for (i in 1..year) {
			val isLeapYear = IsoChronology.INSTANCE.isLeapYear(i.toLong())
			days += if (isLeapYear) 366 else 365
		}
		days += dayOfYear
		return days
	}

val LocalDate.allMonths: Float
	get() {
		val months = year * 12 + monthValue
		return months + dayOfYear / lengthOfYear().toFloat()
	}

val LocalDate.allWeeks: Float
	get() {
		return allDays / 7.0f
	}