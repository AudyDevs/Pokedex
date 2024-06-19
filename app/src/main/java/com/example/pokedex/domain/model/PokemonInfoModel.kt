package com.example.pokedex.domain.model

data class PokemonInfoModel(
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val legacyCries: String,
    val latestCries: String,
    val baseExperience: Int,
    val hpStat: Int,
    val attackStat: Int,
    val defenseStat: Int,
    val specialAttackStat: Int,
    val specialDefenseStat: Int,
    val speedStat: Int,
    val imageLittle: String,
    val imageBig: String,
    val image3D: String,
    val height: Int,
    val weight: Int
)