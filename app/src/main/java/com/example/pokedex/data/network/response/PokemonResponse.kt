package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.Ability
import com.example.pokedex.data.network.response.subresponse.Cries
import com.example.pokedex.data.network.response.subresponse.Form
import com.example.pokedex.data.network.response.subresponse.GameIndice
import com.example.pokedex.data.network.response.subresponse.Move
import com.example.pokedex.data.network.response.subresponse.Species
import com.example.pokedex.data.network.response.subresponse.Sprites
import com.example.pokedex.data.network.response.subresponse.Stat
import com.example.pokedex.data.network.response.subresponse.Type
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("cries") val cries: Cries,
    @SerializedName("forms") val forms: List<Form>,
    @SerializedName("game_indices") val gameIndices: List<GameIndice>,
    @SerializedName("height") val height: Int,
    @SerializedName("held_items") val heldItems: List<Any>,
    @SerializedName("id") val id: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("name") val name: String,
    @SerializedName("order") val order: Int,
    @SerializedName("past_abilities") val pastAbilities: List<Any>,
    @SerializedName("past_types") val pastTypes: List<Any>,
    @SerializedName("species") val species: Species,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("weight") val weight: Int
)