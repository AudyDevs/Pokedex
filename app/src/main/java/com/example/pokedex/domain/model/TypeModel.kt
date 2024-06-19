package com.example.pokedex.domain.model

data class TypeModel(
    val id: Int,
    val name: String,
    val typeSlot: Int,
    val namePokemon: String,
    val doubleDamageFrom: List<String>,
    val doubleDamageTo: List<String>,
    val halfDamageFrom: List<String>,
    val halfDamageTo: List<String>,
    val noDamageFrom: List<String>,
    val noDamageTo: List<String>
)