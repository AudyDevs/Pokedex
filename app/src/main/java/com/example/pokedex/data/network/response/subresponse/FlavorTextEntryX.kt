package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class FlavorTextEntryX(
    @SerializedName("flavor_text") val flavorText: String,
    @SerializedName("language") val language: LanguageXX,
    @SerializedName("version") val version: VersionX
)