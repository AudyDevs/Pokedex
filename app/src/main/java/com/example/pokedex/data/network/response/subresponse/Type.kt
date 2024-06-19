package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: TypeX
)