package com.example.pokedex.domain.model

data class PokemonModel(
    val pokemonInfoModel: PokemonInfoModel?,
    val typeModel: List<TypeModel>?,
    val abilityModel: List<AbilityModel>?,
    val speciesModel: SpeciesModel?,
    val evolutionModel: List<EvolutionModel>?
)