package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class NameXX(
    @SerializedName("language") val language: LanguageXX,
    @SerializedName("name") val name: String
)