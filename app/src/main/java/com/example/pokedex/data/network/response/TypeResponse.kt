package com.example.pokedex.data.network.response

import com.example.pokedex.data.network.response.subresponse.DamageRelations
import com.example.pokedex.data.network.response.subresponse.GameIndiceX
import com.example.pokedex.data.network.response.subresponse.GenerationX
import com.example.pokedex.data.network.response.subresponse.MoveDamageClass
import com.example.pokedex.data.network.response.subresponse.MoveXX
import com.example.pokedex.data.network.response.subresponse.Name
import com.example.pokedex.data.network.response.subresponse.Pokemon
import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("damage_relations") val damageRelations: DamageRelations,
    @SerializedName("game_indices") val gameIndices: List<GameIndiceX>,
    @SerializedName("generation") val generation: GenerationX,
    @SerializedName("id") val id: Int,
    @SerializedName("move_damage_class") val moveDamageClass: MoveDamageClass,
    @SerializedName("moves") val moves: List<MoveXX>,
    @SerializedName("name") val name: String,
    @SerializedName("names") val names: List<Name>,
    @SerializedName("past_damage_relations") val pastDamageRelations: List<Any>,
    @SerializedName("pokemon") val pokemon: List<Pokemon>
)