package com.example.pokedex.domain.state

import com.example.pokedex.domain.model.PokemonListModel

sealed class PokemonListState {
    data object Loading : PokemonListState()
    data class Success(var pokemon: List<PokemonListModel>) : PokemonListState()
    data object Error : PokemonListState()
}