package kanti.altdem

import android.app.DatePickerDialog
import android.icu.number.Precision
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kanti.altdem.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.time.LocalDate
import java.util.Calendar
import kotlin.math.round

class MainActivity : AppCompatActivity() {

	private lateinit var settings: Settings

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		settings = Settings(this)
	}

	override fun onResume() {
		super.onResume()

		var startDate = settings.startDate
		var endDate = settings.endDate

		if (startDate == null || endDate == null) {
			DatePickerDialog(this).apply {
				setOnDateSetListener { _, year, month, day ->
					startDate = LocalDate.of(year, month + 1, day)
					endDate = startDate!!.toEndDate()
					binding.startDate.text = startDate!!.toRuString()
					binding.endDate.text = endDate!!.toRuString()

					endDate?.also(::showRemainingDays)

					settings.startDate = startDate
					settings.endDate = endDate
				}
			}.show()
			return
		}

		binding.startDate.text = startDate?.toRuString() ?: ""
		binding.endDate.text = endDate?.toRuString() ?: ""
		endDate?.also(::showRemainingDays)
	}

	private fun showRemainingDays(endDate: LocalDate) {
		val nowDate = LocalDate.now()
		val daysDif = endDate.allDays - nowDate.allDays
		val monthsDif = endDate.allMonths - nowDate.allMonths
		val weeksDef = endDate.allWeeks - nowDate.allWeeks

		val decFor = DecimalFormat("#.##")
		binding.daysLeft.text = daysDif.toString()
		binding.monthsLeft.text = decFor.format(monthsDif)
		binding.weeksLeft.text = decFor.format(weeksDef)
	}
}