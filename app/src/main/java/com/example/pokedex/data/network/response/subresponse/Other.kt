package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world") val dreamWorld: DreamWorld,
    @SerializedName("home") val home: Home,
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
    @SerializedName("showdown") val showdown: Showdown
)