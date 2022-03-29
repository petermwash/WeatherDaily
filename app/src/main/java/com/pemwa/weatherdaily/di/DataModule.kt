package com.pemwa.weatherdaily.di

import android.content.Context
import com.pemwa.weatherdaily.data.datastore.DataStoreManager
import com.pemwa.weatherdaily.data.datastore.DataStoreManagerImpl
import com.pemwa.weatherdaily.data.db.WeatherDao
import com.pemwa.weatherdaily.data.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Data Module class
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Provides an instance of app's room database
     * @param context app context
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        WeatherDatabase.create(context)

    /**
     * Provides an instance of the database DAO
     * @param database app context
     */
    @Provides
    @Singleton
    fun provideDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    /**
     * Provides an instance of app's data store
     * @param appContext app context
     */
    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManagerImpl(appContext)
}
