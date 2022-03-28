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

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        WeatherDatabase.create(context)

    @Provides
    @Singleton
    fun provideDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManagerImpl(appContext)
}
