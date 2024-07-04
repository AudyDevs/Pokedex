package com.example.pokedex.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.pokedex.di.Constants.ONE_COLUMN_LIST
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStorePreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val PREFERENCE_KEY_USER = "PK_POKEDEX_CONFIG"
        const val DARK_MODE = "DARK_MODE"
        const val POKEMON_TYPE = "POKEMON_TYPE"
        const val COLUMNS_LIST = "COLUMNS_LIST"
    }

    private object PreferenceKeys {
        val darkMode = booleanPreferencesKey(DARK_MODE)
        val pokemonType = stringPreferencesKey(POKEMON_TYPE)
        val columnsList = intPreferencesKey(COLUMNS_LIST)
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

    // Columns List
    suspend fun readDataStoreColumnsList(): Int = runBlocking {
        val preferences = dataStore.data.first()
        return@runBlocking preferences[PreferenceKeys.columnsList] ?: ONE_COLUMN_LIST
    }

    suspend fun saveDataStoreColumnsList(columnsList: Int) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.columnsList] = columnsList
        }
    }
}