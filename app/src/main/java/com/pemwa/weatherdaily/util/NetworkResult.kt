package com.pemwa.weatherdaily.util

/**
 * A generic sealed class holding the possible response types from api
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val error: Throwable? = null
) {

    /**
     * Loading state
     */
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)

    /**
     * Success state
     */
    class Success<T>(data: T?) : NetworkResult<T>(data)

    /**
     * Error state
     */
    class Error<T>(throwable: Throwable, data: T? = null) : NetworkResult<T>(data, throwable)

}
