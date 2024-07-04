package com.example.pokedex.data.network.response.subresponse

import com.google.gson.annotations.SerializedName

data class EvolvesTo(
    @SerializedName("evolution_details") val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to") val evolvesTo: List<EvolvesTo>,
    @SerializedName("is_baby") val isBaby: Boolean,
    @SerializedName("species") val species: SpeciesXX
)