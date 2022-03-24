package com.pemwa.weatherdaily.util

/**
 * A generic sealed class holding the possible response types from api
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    /**
     * Loading state
     */
    class Loading<T> : NetworkResult<T>()

    /**
     * Success state
     */
    class Success<T>(data: T?) : NetworkResult<T>(data)

    /**
     * Error state
     */
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)

}
