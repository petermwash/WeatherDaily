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

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _currentResponse: MutableLiveData<NetworkResult<CurrentWeather>> = MutableLiveData()
    val currentResponse: LiveData<NetworkResult<CurrentWeather>> = _currentResponse

    private val _forecastWeather: MutableLiveData<NetworkResult<ForecastWeather>> = MutableLiveData()
    val forecastWeather: LiveData<NetworkResult<ForecastWeather>> = _forecastWeather

    fun fetchCurrentWeather() = viewModelScope.launch {
        repository.getCurrentWeather().collect { value ->
            _currentResponse.value = value
        }
    }

    fun fetchForecastWeather() = viewModelScope.launch {
        repository.getForecastWeather().collect { value ->
            _forecastWeather.value = value
        }
    }
}
