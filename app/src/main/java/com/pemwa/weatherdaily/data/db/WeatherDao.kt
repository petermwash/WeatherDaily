package com.pemwa.weatherdaily.data.db

import androidx.room.*
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    /**
     * Updates current weather data with fresh data from the network
     * @param currentWeather current weather data fetched from the network
     */
    @Transaction
    suspend fun refreshCurrentWeather(currentWeather: CurrentWeather) {
        currentWeather.let {
            deleteCurrentWeather()
            updateCurrentWeather(it)
        }
    }

    /**
     * Updates forecast weather data with fresh data from the network
     * @param forecastWeather forecast weather data fetched from the network
     */
    @Transaction
    suspend fun refreshForecastWeather(forecastWeather: ForecastWeather) {
        forecastWeather.let {
            deleteForecastWeather()
            updateForeCastWeather(it)
        }
    }

    /**
     * A mapping function to update current weather data
     * @param currentWeather current weather data fetched from the network
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrentWeather(currentWeather: CurrentWeather)

    /**
     * A mapping function to update forecast weather data
     * @param forecastWeather forecast weather data fetched from the network
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateForeCastWeather(forecastWeather: ForecastWeather)

    /**
     * Deletes the existing data from the current_weather_table
     */
    @Query("DELETE FROM current_weather_table")
    suspend fun deleteCurrentWeather()

    /**
     * Deletes the existing data from the forecast_weather_table
     */
    @Query("DELETE FROM forecast_weather_table")
    suspend fun deleteForecastWeather()

    /**
     * Gets current weather data from the database
     */
    @Query("SELECT * FROM current_weather_table")
    fun getCurrentWeather(): Flow<CurrentWeather>

    /**
     * Gets forecast weather data from the database
     */
    @Query("SELECT * FROM forecast_weather_table")
    fun getForecastWeather(): Flow<ForecastWeather>
}