package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_female") val frontFemale: String
)