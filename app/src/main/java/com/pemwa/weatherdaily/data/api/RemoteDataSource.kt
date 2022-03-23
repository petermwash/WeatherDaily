package com.pemwa.weatherdaily.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getCurrentWeather() = weatherService.getCurrentWeather()

    suspend fun getForecastWeather() = weatherService.getForecastWeather()

}
