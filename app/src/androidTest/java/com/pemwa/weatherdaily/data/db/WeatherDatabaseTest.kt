package com.pemwa.weatherdaily.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pemwa.weatherdaily.model.*
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Test db
 */
@RunWith(AndroidJUnit4::class)
class WeatherDatabaseTest : TestCase() {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: WeatherDatabase

    /**
     * Set up required resources
     */
    @Before
    public override fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context, WeatherDatabase::class.java
        ).build()
        weatherDao = db.weatherDao()
    }

    /**
     * Clean up after test
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /**
     * Can write and read from current weather table
     */
    @Test
    fun writeAndReadCurrentWeather() = runBlocking {
        val currentWeather = CurrentWeather(
            "Name",
            listOf(Weather(1, "Rain", "Rainy", "0d")),
            Main(23.5F, 23.0F, 22.8F, 23.7F, 10, 36)
        )
        weatherDao.updateCurrentWeather(currentWeather)
        val currentWeatherResult = weatherDao.getCurrentWeather().first()
        Assert.assertEquals(currentWeatherResult, currentWeather)
    }

    /**
     * Can write and read from forecast weather table
     */
    @Test
    fun writeAndReadForecastWeather() = runBlocking {
        val forecastWeather = ForecastWeather(
            1,200, "", "", listOf(
                Data(22222, DataMain(23.5F, 23.0F, 22.8F, 23.7F, 10, 36),
                    listOf(Weather(1, "Rain", "Rainy", "0d")),
                    ""
                )
            )
        )
        weatherDao.updateForeCastWeather(forecastWeather)
        val forecastWeatherResult = weatherDao.getForecastWeather().first()
        Assert.assertEquals(forecastWeatherResult, forecastWeather)
    }
}