package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegaRubyAlphaSapphire: OmegarubyAlphasapphire,
    @SerializedName("x-y") val xy: XY
)