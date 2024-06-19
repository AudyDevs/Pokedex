package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import javax.inject.Inject

class SavePokemonTypeUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(pokemonType: String) =
        dataStoreRepository.savePokemonType(pokemonType)
}