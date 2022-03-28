package com.pemwa.weatherdaily.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * CurrentWeather object
 */
@Entity(tableName = "current_weather_table")
data class CurrentWeather(
    @PrimaryKey @SerializedName("name") val name: String,
    @SerializedName("weather") val weather: List<Weather>,
    @Embedded @SerializedName("main") val main: Main,
)

/**
 * Weather object
 */
data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val weatherMain: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

/**
 * Main object
 */
data class Main(
    @SerializedName("temp") val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)
