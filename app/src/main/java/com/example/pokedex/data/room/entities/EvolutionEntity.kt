package com.example.pokedex.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evolution")
data class EvolutionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "idEvolution") val idEvolution: String = "",
    @ColumnInfo(name = "namePokemon") val namePokemon: String = "",
    @ColumnInfo(name = "idPokemonEvolution") val idPokemonEvolution: String = "",
    @ColumnInfo(name = "nameEvolution") val nameEvolution: String = "",
    @ColumnInfo(name = "itemEvolution") val itemEvolution: String = "",
    @ColumnInfo(name = "minLevel") val minLevel: Int = 0,
    @ColumnInfo(name = "trigger") val trigger: String = "",
    @ColumnInfo(name = "happiness") val happiness: Int = 0,
    @ColumnInfo(name = "timeOfDay") val timeOfDay: String = "",
    @ColumnInfo(name = "location") val location: String = ""
)