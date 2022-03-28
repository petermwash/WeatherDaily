package com.pemwa.weatherdaily.data.datastore

interface DataStoreManager {
    suspend fun putString(key: String, value: String)
    suspend fun getString(key: String): String?
}
