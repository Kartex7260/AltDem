package kanti.altdem

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.time.LocalDate
import java.util.Calendar

class Settings(context: Context) {

	private val sharPref: SharedPreferences

	private val startYearTag: String = "start_year_tag"
	private val startMonthTag: String = "start_month_tag"
	private val startDayTag: String = "start_day_tag"

	private val endYearTag: String = "end_year_tag"
	private val endMonthTag: String = "end_month_tag"
	private val endDayTag: String = "end_day_tag"

	init {
		sharPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
	}

	var startDate: LocalDate?
		get() {
			val year = sharPref.getInt(startYearTag, -1)
			val month = sharPref.getInt(startMonthTag, -1)
			val day = sharPref.getInt(startDayTag, -1)
			if (year == -1 || month == -1 || day == -1)
				return null
			return LocalDate.of(year, month, day)
		}
		set(value) = sharPref.edit {
			if (value == null)
				return
			putInt(startYearTag, value.year)
			putInt(startMonthTag, value.monthValue)
			putInt(startDayTag, value.dayOfMonth)
		}

	var endDate: LocalDate?
		get() {
			val year = sharPref.getInt(endYearTag, -1)
			val month = sharPref.getInt(endMonthTag, -1)
			val day = sharPref.getInt(endDayTag, -1)
			if (year == -1 || month == -1 || day == -1)
				return null
			return LocalDate.of(year, month, day)
		}
		set(value) = sharPref.edit {
			if (value == null)
				return
			putInt(endYearTag, value.year)
			putInt(endMonthTag, value.monthValue)
			putInt(endDayTag, value.dayOfMonth)
		}

}