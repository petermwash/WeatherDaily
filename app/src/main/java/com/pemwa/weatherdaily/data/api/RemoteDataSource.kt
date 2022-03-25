package com.pemwa.weatherdaily.data.api

import com.pemwa.weatherdaily.BuildConfig
import javax.inject.Inject

/**
 * Weather Remote data source class
 */
class RemoteDataSource @Inject constructor(private val weatherService: WeatherService) {

    private val appId = BuildConfig.API_KEY

    /**
     * Suspend to fetch current weather
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getCurrentWeather(
        lat: String,
        lon: String
    ) = weatherService.getCurrentWeather(lat, lon, appId)

    /**
     * Suspend function to fetch weather forecast
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getForecastWeather(
        lat: String,
        lon: String
    ) = weatherService.getForecastWeather(lat, lon, appId)

}
