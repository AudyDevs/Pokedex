package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("pokemon") val pokemon: PokemonX,
    @SerializedName("slot") val slot: Int
)