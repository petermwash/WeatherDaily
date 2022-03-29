package com.pemwa.weatherdaily.data.datastore

/**
 * Data store interface holding signature methods for managing the data store
 */
interface DataStoreManager {
    /**
     * Method signature for storing a String value
     */
    suspend fun putString(key: String, value: String)
    /**
     * Method signature for fetching a String value
     */
    suspend fun getString(key: String): String?
}
