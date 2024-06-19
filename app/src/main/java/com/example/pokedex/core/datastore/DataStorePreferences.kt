package com.example.pokedex.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStorePreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val PREFERENCE_KEY_USER = "PK_POKEDEX_CONFIG"
        const val DARK_MODE = "DARK_MODE"
        const val POKEMON_TYPE = "POKEMON_TYPE"
    }

    private object PreferenceKeys {
        val darkMode = booleanPreferencesKey(DARK_MODE)
        val pokemonType = stringPreferencesKey(POKEMON_TYPE)
    }

    // Dark mode
    suspend fun readDataStoreDarkMode(): Boolean = runBlocking {
        val preferences = dataStore.data.first()
        return@runBlocking preferences[PreferenceKeys.darkMode] ?: false
    }

    suspend fun saveDataStoreDarkMode(darkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.darkMode] = darkMode
        }
    }

    // Pokemon Type
    suspend fun readDataStorePokemonType(): String = runBlocking {
        val preferences = dataStore.data.first()
        return@runBlocking preferences[PreferenceKeys.pokemonType].orEmpty()
    }

    suspend fun saveDataStorePokemonType(pokemonType: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.pokemonType] = pokemonType
        }
    }
}