package com.example.pokedex.domain.model

data class SpeciesModel(
    val id: Int,
    val name: String,
    val baseHappiness: Int,
    val captureRate: Int,
    val idEvolutionChain: String,
    val description: String,
    val pokemonClass: String,
    val habitat: String,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean,
)