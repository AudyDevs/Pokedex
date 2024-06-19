package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class GameIndiceX(
    @SerializedName("game_index") val gameIndex: Int,
    @SerializedName("generation") val generation: GenerationX
)