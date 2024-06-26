package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.Color
import com.example.pokedex.data.network.response.subresponse.EggGroup
import com.example.pokedex.data.network.response.subresponse.EvolutionChain
import com.example.pokedex.data.network.response.subresponse.FlavorTextEntryX
import com.example.pokedex.data.network.response.subresponse.Genera
import com.example.pokedex.data.network.response.subresponse.GenerationXX
import com.example.pokedex.data.network.response.subresponse.GrowthRate
import com.example.pokedex.data.network.response.subresponse.Habitat
import com.example.pokedex.data.network.response.subresponse.NameXX
import com.example.pokedex.data.network.response.subresponse.PalParkEncounter
import com.example.pokedex.data.network.response.subresponse.PokedexNumber
import com.example.pokedex.data.network.response.subresponse.Shape
import com.example.pokedex.data.network.response.subresponse.Variety
import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("base_happiness") val baseHappiness: Int,
    @SerializedName("capture_rate") val captureRate: Int,
    @SerializedName("color") val color: Color,
    @SerializedName("egg_groups") val eggGroups: List<EggGroup>,
    @SerializedName("evolution_chain") val evolutionChain: EvolutionChain,
    @SerializedName("evolves_from_species") val evolvesFromSpecies: Any,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntryX>,
    @SerializedName("form_descriptions") val formDescriptions: List<Any>,
    @SerializedName("forms_switchable") val formsSwitchable: Boolean,
    @SerializedName("gender_rate") val genderRate: Int,
    @SerializedName("genera") val genera: List<Genera>,
    @SerializedName("generation") val generation: GenerationXX,
    @SerializedName("growth_rate") val growthRate: GrowthRate,
    @SerializedName("habitat") val habitat: Habitat,
    @SerializedName("has_gender_differences") val hasGenderDifferences: Boolean,
    @SerializedName("hatch_counter") val hatchCounter: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("is_baby") val isBaby: Boolean,
    @SerializedName("is_legendary") val isLegendary: Boolean,
    @SerializedName("is_mythical") val isMythical: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<NameXX>,
    @SerializedName("order") val order: Int,
    @SerializedName("pal_park_encounters") val palParkEncounters: List<PalParkEncounter>,
    @SerializedName("pokedex_numbers") val pokedexNumbers: List<PokedexNumber>,
    @SerializedName("shape") val shape: Shape,
    @SerializedName("varieties") val varieties: List<Variety>
)