package com.pemwa.weatherdaily.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.pemwa.weatherdaily.R
import dagger.hilt.android.AndroidEntryPoint
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat


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

        window.attributes.let {
            it.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
            window.attributes = it
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

    }

}
