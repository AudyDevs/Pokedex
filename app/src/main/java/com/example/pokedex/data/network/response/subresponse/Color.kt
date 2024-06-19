package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)