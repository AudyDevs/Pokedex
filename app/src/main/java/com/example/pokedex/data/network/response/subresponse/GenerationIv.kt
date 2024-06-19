package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver") val heartGoldSoulSilver: HeartgoldSoulsilver,
    @SerializedName("platinum") val platinum: Platinum
)