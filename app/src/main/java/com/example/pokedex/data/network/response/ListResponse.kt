package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.Result
import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String,
    @SerializedName("results") val results: List<Result>
)