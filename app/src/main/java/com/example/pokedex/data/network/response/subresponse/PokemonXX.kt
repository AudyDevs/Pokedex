package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class PokemonXX(
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("pokemon") val pokemon: PokemonXXX,
    @SerializedName("slot") val slot: Int
)