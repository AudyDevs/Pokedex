package com.example.pokedex.domain.model

data class PokemonListModel(
    val id: Int,
    val name: String,
    val url: String,
    val imageLittle: String,
    val imageBig: String,
    var typeSlot1: String,
    var typeSlot2: String
)