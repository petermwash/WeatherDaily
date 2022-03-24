package com.pemwa.weatherdaily.data.repository

import com.pemwa.weatherdaily.base.BaseApiResponse
import com.pemwa.weatherdaily.data.api.RemoteDataSource
import com.pemwa.weatherdaily.model.CurrentWeather
import com.pemwa.weatherdaily.model.ForecastWeather
import com.pemwa.weatherdaily.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Weather Repository class
 */
@ActivityRetainedScoped
class WeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    /**
     * Suspend to fetch current weather
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getCurrentWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<CurrentWeather>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCurrentWeather(lat, lon) })
        }.flowOn(Dispatchers.IO)
    }

    /**
     * Suspend function to fetch weather forecast
     * @param lat current location latitude
     * @param lon current location longitude
     */
    suspend fun getForecastWeather(
        lat: String,
        lon: String
    ): Flow<NetworkResult<ForecastWeather>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getForecastWeather(lat, lon) })
        }.flowOn(Dispatchers.IO)
    }

}
