package com.example.pokedex.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.pokedex.core.datastore.DataStorePreferences
import com.example.pokedex.data.DataStoreRepositoryImpl
import com.example.pokedex.domain.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    private val Context.dataStoreUser: DataStore<Preferences> by preferencesDataStore(name = DataStorePreferences.PREFERENCE_KEY_USER)

    @Provides
    @Singleton
    fun provideDataStoreUser(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStoreUser
    }

    @Provides
    fun provideDataStoreRepository(dataStorePreferences: DataStorePreferences): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStorePreferences)
    }
}