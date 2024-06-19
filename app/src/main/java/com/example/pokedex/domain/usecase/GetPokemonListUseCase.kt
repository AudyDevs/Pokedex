package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke() = pokemonRepository.getPokemonList()
}