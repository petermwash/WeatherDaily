package com.pemwa.weatherdaily.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pemwa.weatherdaily.model.Data
import com.pemwa.weatherdaily.model.Weather
import java.lang.reflect.Type

/**
 * Type Converter class
 */
class DataTypeConverters {

    /**
     * Converts List<Weather> to String
     * @param weather List<Weather>
     * @return String
     */
    @TypeConverter
    fun fromWeatherList(weather: List<Weather>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Weather?>?>() {}.type
        return gson.toJson(weather, type)
    }

    /**
     * Converts String type to List<Weather>
     * @param weatherString
     * @return List<Weather>
     */
    @TypeConverter
    fun toWeatherList(weatherString: String): List<Weather> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(weatherString, type)
    }

    /**
     * Converts List<Data> to String
     * @param dataWeather List<Data>
     * @return String
     */
    @TypeConverter
    fun fromDataWeatherList(dataWeather: List<Data>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Data?>?>() {}.type
        return gson.toJson(dataWeather, type)
    }

    /**
     * Converts String type to List<Data>
     * @param dataWeatherString
     * @return List<Data>
     */
    @TypeConverter
    fun toDataWeatherList(dataWeatherString: String): List<Data> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Data>>() {}.type
        return gson.fromJson(dataWeatherString, type)
    }
}
