package com.pemwa.weatherdaily.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pemwa.weatherdaily.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Weather Activity class
 */
@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    /**
     * sets the activity view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }

}
