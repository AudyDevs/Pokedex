package com.example.pokedex.domain

import com.example.pokedex.domain.state.PokemonInfoState
import com.example.pokedex.domain.state.PokemonListState

interface PokemonRepository {

    suspend fun getPokemonList(): PokemonListState

    suspend fun getPokemonByID(idPokemon: Int): String

    suspend fun getPokemonInfo(namePokemon: String): PokemonInfoState
}