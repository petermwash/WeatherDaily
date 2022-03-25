package com.pemwa.weatherdaily.data.api

import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Weather Service Interface
 */
interface WeatherService {

    /**
     * Get method to fetch current weather from the api
     * @param lat current location latitude
     * @param lon current location longitude
     * @param appId API key
     */
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String = "metric"
    ): Response<CurrentWeather>

    /**
     * Get method to fetch weather forecast from the api
     * @param lat current location latitude
     * @param lon current location longitude
     * @param appId API key
     */
    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("appid") appId:String,
        @Query("units") units: String = "metric"
    ): Response<ForecastWeather>

}
