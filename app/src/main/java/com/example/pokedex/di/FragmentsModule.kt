package com.example.pokedex.di

import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FragmentsModule {

    @Singleton
    @Provides
    fun provideFragmentsManager() = FragmentsManager()
}