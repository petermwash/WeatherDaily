package com.pemwa.weatherdaily.base

import com.pemwa.weatherdaily.util.NetworkResult
import retrofit2.Response

/**
 * Encapsulate basic API response checks from the ViewModel class
 */
abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    body?.let {
                        return NetworkResult.Success(body)
                    }
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")

}
