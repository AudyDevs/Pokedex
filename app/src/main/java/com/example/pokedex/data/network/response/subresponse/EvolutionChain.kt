package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("url") val url: String
)