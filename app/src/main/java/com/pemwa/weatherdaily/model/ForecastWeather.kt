package com.pemwa.weatherdaily.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * ForecastWeather object
 */
@Entity(tableName = "forecast_weather_table")
data class ForecastWeather(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("cod") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("cnt") val cnt: String,
    @SerializedName("list") val weather: List<Data>,
)

/**
 * Data object
 */
data class Data(
    @SerializedName("dt") val date: Long,
    @Embedded @SerializedName("main") val dataMain: DataMain,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("dt_txt") val dateText: String
)

/**
 * DataMain object
 */
data class DataMain(
    @SerializedName("temp") val temp: Float,
    @SerializedName("feels_like") val feelsLike: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int
)
