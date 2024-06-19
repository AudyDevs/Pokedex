package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("language") val language: Language,
    @SerializedName("name") val name: String
)