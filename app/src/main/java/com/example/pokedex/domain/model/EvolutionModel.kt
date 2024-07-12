package com.example.pokedex.domain.model

data class EvolutionModel(
    val id: Int,
    val idEvolution: String,
    val idPokemonOrigin: String,
    val namePokemon: String,
    val idPokemonEvolution: String,
    val nameEvolution: String,
    val itemEvolution: String,
    val minLevel: Int,
    val trigger: String,
    val happiness: Int,
    val timeOfDay: String,
    val location: String
)