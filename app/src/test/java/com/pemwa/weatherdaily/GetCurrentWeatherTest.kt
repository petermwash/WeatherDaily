package com.pemwa.weatherdaily

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.pemwa.weatherdaily.data.api.RemoteDataSource
import com.pemwa.weatherdaily.data.datastore.DataStoreManager
import com.pemwa.weatherdaily.data.db.LocalDataSource
import com.pemwa.weatherdaily.data.repository.WeatherRepository
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.Main
import com.pemwa.weatherdaily.model.Weather
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito.`when`
import retrofit2.Response

/**
 * Test get current weather api call
 */
@RunWith(JUnit4::class)
class GetCurrentWeatherTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    private lateinit var repository: WeatherRepository
    private lateinit var mockedResponse: CurrentWeather

    private val testLat = "1"
    private val testLon = "36"
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val fakeApp = Mockito.mock(Application::class.java)
    private val dataStore = Mockito.mock(DataStoreManager::class.java)

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * initialize values required for testing
     */
    @Before
    fun init() {
        repository = WeatherRepository(remoteDataSource, localDataSource, fakeApp, dataStore)
    }

    /**
     * Test get current weather success api call
     */
    @Test
    fun test_canGetCurrentWeatherSuccess() {
        mockedResponse = CurrentWeather(
            "Name",
            listOf(Weather(1, "Rain", "Rainy", "0d")),
            Main(23.5F, 23.0F, 22.8F, 23.7F, 10, 36)
        )
        runBlocking {
            `when`(repository.remoteDataSource.getCurrentWeather(testLat, testLon))
                .thenReturn(Response.success(mockedResponse))
        }
        val response = runBlocking { repository.remoteDataSource.getCurrentWeather(testLat, testLon) }
        val resultResponse = response.body()
        Assert.assertNotNull(response)
        Assert.assertTrue(resultResponse!! == mockedResponse)
    }

    /**
     * tears down the mock server when tests are done
     */
    @After
    fun tearDown() {
        //
    }
}