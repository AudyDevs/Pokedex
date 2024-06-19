package com.example.pokedex.data

import com.example.pokedex.core.datastore.DataStorePreferences
import com.example.pokedex.domain.DataStoreRepository
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : DataStoreRepository {

    override suspend fun readDarkMode(): Boolean {
        return dataStorePreferences.readDataStoreDarkMode()
    }

    override suspend fun saveDarkMode(darkMode: Boolean) {
        dataStorePreferences.saveDataStoreDarkMode(darkMode)
    }

    override suspend fun readPokemonType(): String {
        return dataStorePreferences.readDataStorePokemonType()
    }

    override suspend fun savePokemonType(pokemonType: String) {
        dataStorePreferences.saveDataStorePokemonType(pokemonType)
    }
}