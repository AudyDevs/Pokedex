package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.PokemonRepository
import javax.inject.Inject

class GetPokemonByIDUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(idPokemon: Int) = pokemonRepository.getPokemonByID(idPokemon)
}