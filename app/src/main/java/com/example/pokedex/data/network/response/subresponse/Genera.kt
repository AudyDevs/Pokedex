package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus") val genus: String,
    @SerializedName("language") val language: LanguageXX
)