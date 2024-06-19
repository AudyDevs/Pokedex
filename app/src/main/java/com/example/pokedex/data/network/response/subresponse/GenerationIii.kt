package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class GenerationIii(
    @SerializedName("emerald") val emerald: Emerald,
    @SerializedName("firered-leafgreen") val fireRedLeafGreen: FireredLeafgreen,
    @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire
)