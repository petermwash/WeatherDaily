package com.pemwa.weatherdaily.data.repository

import android.content.Context
import com.pemwa.weatherdaily.data.api.RemoteDataSource
import com.pemwa.weatherdaily.data.datastore.DataStoreManager
import com.pemwa.weatherdaily.data.db.LocalDataSource
import com.pemwa.weatherdaily.util.ConnectivityUtil.isDeviceOnline
import com.pemwa.weatherdaily.util.Constants.Companion.PREF_KEY_LAST_UPDATED
import com.pemwa.weatherdaily.util.UtilMethods.Companion.getCurrentTime
import com.pemwa.weatherdaily.util.networkBoundResource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Weather Repository class
 */
@ActivityRetainedScoped
class WeatherRepository @Inject constructor(
    val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val context: Context,
    private val dataStoreRepository: DataStoreManager,
) {

    /**
     * Suspend to fetch current weather
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getCurrentWeather(lat: String, lon: String) = networkBoundResource(
        fetchLocal = {
            localDataSource.getCurrentWeather()
        },
        fetchRemote = {
            delay(1000)
            remoteDataSource.getCurrentWeather(lat, lon)
        },
        saveRemoteData = { response ->
            dataStoreRepository.putString(PREF_KEY_LAST_UPDATED, getCurrentTime())
            response.body()?.let { it -> localDataSource.updateCurrentWeather(it) }
        },
    )

    /**
     * Suspend function to fetch weather forecast
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getForecastWeather(lat: String,lon: String) = networkBoundResource(
        fetchLocal = {
            localDataSource.getForecastWeather()
        },
        fetchRemote = {
            delay(1000)
            remoteDataSource.getForecastWeather(lat, lon)
        },
        saveRemoteData = { response ->
            dataStoreRepository.putString(PREF_KEY_LAST_UPDATED, getCurrentTime())
            response.body()?.let { it -> localDataSource.updateForecastWeather(it) }
        },
        shouldFetchRemote = {
            isDeviceOnline(context)
        }
    )

}
