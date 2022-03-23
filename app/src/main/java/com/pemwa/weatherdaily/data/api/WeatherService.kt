package com.pemwa.weatherdaily.data.api

import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {

    @GET("current")
    suspend fun getCurrentWeather(): Response<CurrentWeather>

    @GET("forecast")
    suspend fun getForecastWeather(): Response<ForecastWeather>

}
