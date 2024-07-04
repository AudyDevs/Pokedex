package com.example.pokedex.domain

interface DataStoreRepository {

    suspend fun readDarkMode(): Boolean

    suspend fun saveDarkMode(darkMode: Boolean)

    suspend fun readPokemonType(): String

    suspend fun savePokemonType(pokemonType: String)

    suspend fun readColumnsList(): Int

    suspend fun saveColumnsList(columnsList: Int)
}