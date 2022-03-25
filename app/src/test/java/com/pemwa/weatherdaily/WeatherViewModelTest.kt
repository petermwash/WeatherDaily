package com.pemwa.weatherdaily

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pemwa.weatherdaily.data.repository.WeatherRepository
import com.pemwa.weatherdaily.model.*
import com.pemwa.weatherdaily.util.NetworkResult
import com.pemwa.weatherdaily.viewmodel.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Weather ViewModel test class
 */
@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testLat = "1"
    private val testLon = "36"
    private val fakeApp = Mockito.mock(Application::class.java)
    private val weatherRepository = Mockito.mock(WeatherRepository::class.java)
    private val weatherViewModel = WeatherViewModel(weatherRepository, fakeApp)

    /**
     * Test ViewModel can fetch current weather
     */
    @Test
    fun test_fetchCurrentWeather() = testDispatcher.runBlockingTest {
        val currentWeather = CurrentWeather(
            200,
            "Name",
            listOf(Weather(1, "Rain", "Rainy", "0d")),
            Main(23.5F, 23.0F, 22.8F, 23.7F, 10, 36)
        )
        val response = NetworkResult.Success(currentWeather)
        val channel = Channel<NetworkResult<CurrentWeather>>()
        val flow = channel.consumeAsFlow()
        `when`(weatherRepository.getCurrentWeather(testLat, testLon)).thenReturn(flow)

        this.launch {
            channel.send(response)
        }

        weatherViewModel.fetchCurrentWeather(testLat, testLon)
        Assert.assertFalse(weatherViewModel.currentWeather.value?.data == null)
        Assert.assertEquals(currentWeather, weatherViewModel.currentWeather.value?.data)
    }

    /**
     * Test ViewModel can fetch forecast weather
     */
    @Test
    fun test_fetchForecastWeather() = testDispatcher.runBlockingTest {
        val forecastWeather = ForecastWeather(
            200, "", "", listOf(
                Data(22222, DataMain(23.5F, 23.0F, 22.8F, 23.7F, 10, 36),
                    listOf(Weather(1, "Rain", "Rainy", "0d")),
                    ""
                )
            )
        )
        val response = NetworkResult.Success(forecastWeather)
        val channel = Channel<NetworkResult<ForecastWeather>>()
        val flow = channel.consumeAsFlow()
        `when`(weatherRepository.getForecastWeather(testLat, testLon)).thenReturn(flow)

        this.launch {
            channel.send(response)
        }

        weatherViewModel.fetchForecastWeather(testLat, testLon)
        Assert.assertFalse(weatherViewModel.forecastWeather.value?.data == null)
        Assert.assertEquals(forecastWeather, weatherViewModel.forecastWeather.value?.data)
    }

}
