package com.pemwa.weatherdaily.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pemwa.weatherdaily.data.repository.WeatherRepository
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import com.pemwa.weatherdaily.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Weather ViewModel class
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _currentWeather: MutableLiveData<NetworkResult<CurrentWeather>> = MutableLiveData()
    val currentWeather: LiveData<NetworkResult<CurrentWeather>> = _currentWeather

    private val _forecastWeather: MutableLiveData<NetworkResult<ForecastWeather>> = MutableLiveData()
    val forecastWeather: LiveData<NetworkResult<ForecastWeather>> = _forecastWeather

    /**
     * Fetch current weather
     * @param lat current location latitude
     * @param lon current location longitude
     */
    fun fetchCurrentWeather(
        lat: String,
        lon: String
    ) = viewModelScope.launch {
        repository.getCurrentWeather(lat, lon).collect { value ->
            _currentWeather.value = value
        }
    }

    /**
     * Fetch weather forecast
     * @param lat current location latitude
     * @param lon current location longitude
     */
    fun fetchForecastWeather(
        lat: String,
        lon: String
    ) = viewModelScope.launch {
        repository.getForecastWeather(lat, lon).collect { value ->
            _forecastWeather.value = value
        }
    }
}
