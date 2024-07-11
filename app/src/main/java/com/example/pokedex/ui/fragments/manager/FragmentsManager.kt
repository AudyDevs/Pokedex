package com.example.pokedex.ui.fragments.manager

import com.example.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FragmentsManager @Inject constructor() {

    private val _pokemonInfo = MutableStateFlow<PokemonModel?>(null)
    val pokemonInfo: StateFlow<PokemonModel?> = _pokemonInfo

    fun setPokemonInfo(pokemonInfo: PokemonModel?) {
        _pokemonInfo.value = pokemonInfo
    }
}