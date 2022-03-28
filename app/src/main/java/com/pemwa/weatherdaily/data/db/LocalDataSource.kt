package com.pemwa.weatherdaily.data.db

import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import javax.inject.Inject

/**
 * Weather Local data source class
 */
class LocalDataSource @Inject constructor(private val weatherDao: WeatherDao) {

    /**
     * Suspend function to update the database with the current weather data
     */
    suspend fun updateCurrentWeather(currentWeather: CurrentWeather) {
        weatherDao.refreshCurrentWeather(currentWeather)
    }

    /**
     * Suspend function to update the database with the current weather data
     */
    suspend fun updateForecastWeather(forecastWeather: ForecastWeather) {
        weatherDao.refreshForecastWeather(forecastWeather)
    }

    /**
     * Suspend function to fetch current weather from the db
     */
    fun getCurrentWeather() = weatherDao.getCurrentWeather()

    /**
     * Suspend function to fetch weather forecast from the db
     */
    fun getForecastWeather() = weatherDao.getForecastWeather()
}
