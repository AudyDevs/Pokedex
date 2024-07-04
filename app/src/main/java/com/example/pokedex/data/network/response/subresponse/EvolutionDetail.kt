package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class EvolutionDetail(
    @SerializedName("gender") val gender: Any,
    @SerializedName("held_item") val heldItem: Any,
    @SerializedName("item") val item: Items,
    @SerializedName("known_move") val knownMove: Any,
    @SerializedName("known_move_type") val knownMoveType: Any,
    @SerializedName("location") val location: Locations,
    @SerializedName("min_affection") val minAffection: Any,
    @SerializedName("min_beauty") val minBeauty: Any,
    @SerializedName("min_happiness") val minHappiness: Int,
    @SerializedName("min_level") val minLevel: Int,
    @SerializedName("needs_overworld_rain") val needsOverWorldRain: Boolean,
    @SerializedName("party_species") val partySpecies: Any,
    @SerializedName("party_type") val partyType: Any,
    @SerializedName("relative_physical_stats") val relativePhysicalStats: Any,
    @SerializedName("time_of_day") val timeOfDay: String,
    @SerializedName("trade_species") val tradeSpecies: Any,
    @SerializedName("trigger") val trigger: Trigger,
    @SerializedName("turn_upside_down") val turnUpsideDown: Boolean
)