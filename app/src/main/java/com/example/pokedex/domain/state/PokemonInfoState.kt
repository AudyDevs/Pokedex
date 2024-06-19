package com.example.pokedex.domain.state

import com.example.pokedex.domain.model.PokemonModel

sealed class PokemonInfoState {
    data object Loading : PokemonInfoState()
    data class Success(val pokemonInfo: PokemonModel?) : PokemonInfoState()
    data object Error : PokemonInfoState()
}