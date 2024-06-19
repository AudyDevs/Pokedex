package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.EffectEntry
import com.example.pokedex.data.network.response.subresponse.FlavorTextEntry
import com.example.pokedex.data.network.response.subresponse.Generation
import com.example.pokedex.data.network.response.subresponse.NameX
import com.example.pokedex.data.network.response.subresponse.PokemonXX
import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @SerializedName("effect_changes") val effectChanges: List<Any>,
    @SerializedName("effect_entries") val effectEntries: List<EffectEntry>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("generation") val generation: Generation,
    @SerializedName("id") val id: Int,
    @SerializedName("is_main_series") val isMainSeries: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<NameX>,
    @SerializedName("pokemon") val pokemon: List<PokemonXX>
)