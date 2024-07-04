package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.Chain
import com.google.gson.annotations.SerializedName

data class EvolutionResponse(
    @SerializedName("baby_trigger_item") val babyTriggerItem: Any,
    @SerializedName("chain") val chain: Chain,
    @SerializedName("id") val id: Int
)