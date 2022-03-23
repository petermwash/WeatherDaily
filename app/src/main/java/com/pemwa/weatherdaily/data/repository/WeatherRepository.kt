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

@ActivityRetainedScoped
class WeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getCurrentWeather(): Flow<NetworkResult<CurrentWeather>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getCurrentWeather() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getForecastWeather(): Flow<NetworkResult<ForecastWeather>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getForecastWeather() })
        }.flowOn(Dispatchers.IO)
    }

}
