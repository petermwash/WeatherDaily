package com.pemwa.weatherdaily.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pemwa.weatherdaily.util.Constants.Companion.KEY_PREFERENCES_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = KEY_PREFERENCES_NAME)

/**
 * Implementation of the DataStoreManager interface
 */
class DataStoreManagerImpl @Inject constructor(
    private val context: Context
    ) : DataStoreManager {

    /**
     * Saves a string to data store
     * @param key key tied to the value being saved
     * @param value value to be saved
     */
    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    /**
     * gets a string from data store
     * @param key key tied to the value being retrieved
     * @return String the value
     */
    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}
