package com.pemwa.weatherdaily.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import com.pemwa.weatherdaily.util.Constants.Companion.DB_NAME
import com.pemwa.weatherdaily.util.DataTypeConverters

/**
 * The Room database for the app
 */
@Database(entities = [CurrentWeather::class, ForecastWeather::class], version = 1)
@TypeConverters(DataTypeConverters::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        fun create(context: Context): WeatherDatabase {

            return Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}
