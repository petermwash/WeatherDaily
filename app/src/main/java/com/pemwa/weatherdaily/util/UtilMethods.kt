package com.pemwa.weatherdaily.util

import android.annotation.SuppressLint
import android.util.Log
import com.pemwa.weatherdaily.model.Data
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Contains common util methods
 */
class UtilMethods {

    companion object {

        /**
         * Returns weather type
         * @param weather
         * @return weather type in a Pair
         */
        fun getWeatherType(weather: String): Pair<String, Int> {
            return when {
                weather.contains("Cloud", true) -> Pair("CLOUDY", 1)
                weather.contains("Clear", true) -> Pair("SUNNY", 2)
                else -> Pair("RAINY", 3)
            }
        }

        /**
         * Returns day of week for the date string provided
         * @param dateString date
         * @return day of week as a string
         */
        @SuppressLint("SimpleDateFormat")
        fun getDayOfWeek(dateString: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date!!

            return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) ?: ""
        }

        /**
         * Process the forecast weather data before it's mapped to the ui
         * @param data weather data
         * @return processed weather data as a list of Triple
         */
        fun processForecastData(data: List<Data>): List<Triple<String, Int, String>> {
            val processed = mutableListOf<Triple<String, Int, String>>()
            val mapped = data.map {
                it.date to Triple(getDayOfWeek(it.dateText), it.main.temp, getWeatherType(it.weather[0].main))
            }.toMap().values
            val passed = mutableListOf<String>()
            mapped.forEach {
                when {
                    !passed.contains(it.first) -> {
                        processed.add(Triple(it.first, it.third.second, it.second.toString()))
                        passed.add(it.first)
                    }
                }
            }

            return processed
        }
    }
}
