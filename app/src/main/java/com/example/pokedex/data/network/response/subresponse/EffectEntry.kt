package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class EffectEntry(
    @SerializedName("effect") val effect: String,
    @SerializedName("language") val language: LanguageX,
    @SerializedName("short_effect") val shortEffect: String
)