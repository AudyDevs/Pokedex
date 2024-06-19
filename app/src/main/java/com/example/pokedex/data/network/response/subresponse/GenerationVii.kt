package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    @SerializedName("icons") val icons: Icons,
    @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
)