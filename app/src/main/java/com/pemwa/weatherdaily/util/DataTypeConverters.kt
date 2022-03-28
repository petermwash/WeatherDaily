package com.pemwa.weatherdaily.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pemwa.weatherdaily.model.Data
import com.pemwa.weatherdaily.model.Weather
import java.lang.reflect.Type

class DataTypeConverters {

    @TypeConverter
    fun fromWeatherList(weather: List<Weather>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.toJson(weather, type)
    }

    @TypeConverter
    fun toWeatherList(weatherString: String): List<Weather> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(weatherString, type)
    }

    @TypeConverter
    fun fromDataWeatherList(dataWeather: List<Data>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Data?>?>() {}.type
        return gson.toJson(dataWeather, type)
    }

    @TypeConverter
    fun toDataWeatherList(dataWeatherString: String): List<Data> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Data>>() {}.type
        return gson.fromJson(dataWeatherString, type)
    }
}
