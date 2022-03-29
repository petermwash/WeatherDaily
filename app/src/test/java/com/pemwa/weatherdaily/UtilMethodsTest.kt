package com.pemwa.weatherdaily

import com.google.common.truth.Truth.assertThat
import com.pemwa.weatherdaily.model.Data
import com.pemwa.weatherdaily.model.DataMain
import com.pemwa.weatherdaily.model.Weather
import com.pemwa.weatherdaily.util.UtilMethods.Companion.getCurrentTime
import com.pemwa.weatherdaily.util.UtilMethods.Companion.getDayOfWeek
import com.pemwa.weatherdaily.util.UtilMethods.Companion.getWeatherType
import com.pemwa.weatherdaily.util.UtilMethods.Companion.processForecastData
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Tests the util methods
 */
class UtilMethodsTest {

    /**
     * Tests can return current time
     */
    @Test
    fun test_canReturnsCurrentTime() {
         val currentTime = getCurrentTime()

        assertThat(currentTime).hasLength(19)
    }

    /**
     * Tests can return weather type
     */
    @Test
    fun test_returnsWeatherType() {
        val weatherType = getWeatherType("Cloud")

        assertThat(weatherType.first).isNotEmpty()
        assertThat(weatherType.second).isIn(0..3)
    }

    /**
     * Tests can day of week
     */
    @Test
    fun test_returnsDayWeek() {
        val dayOfWeek = getDayOfWeek("2022-03-29 10:57:10")

        assertThat(dayOfWeek).isNotEmpty()
        assertThat(dayOfWeek).isEqualTo("Tuesday")
    }

    /**
     * Tests can return weather type
     */
    @Test
    fun test_canProcessForecastData() {
        val forecastData = listOf(
            Data(1647356400, DataMain(23.5F, 23.0F, 22.8F, 23.7F, 10, 36),
                listOf(Weather(1, "Rain", "Rainy", "0d")),
                "2022-03-15 15:00:00"
            ),
            Data(1647356400, DataMain(23.5F, 23.0F, 22.8F, 23.7F, 10, 36),
                listOf(Weather(1, "Rain", "Rainy", "0d")),
                "2022-03-15 15:00:00"
            ),
            Data(1647356400, DataMain(23.5F, 23.0F, 22.8F, 23.7F, 10, 36),
                listOf(Weather(1, "Rain", "Rainy", "0d")),
                "2022-03-15 15:00:00"
            )
        )
        val processedData = processForecastData(forecastData)

        assertThat(processedData).isNotEmpty()
        assertThat(processedData[0].second).isEqualTo(3)
    }
}
