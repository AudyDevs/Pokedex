package com.example.pokedex.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "baseHappiness") val baseHappiness: Int = 0,
    @ColumnInfo(name = "captureRate") val captureRate: Int = 0,
    @ColumnInfo(name = "idEvolutionChain") val idEvolutionChain: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "pokemonClass") val pokemonClass: String = "",
    @ColumnInfo(name = "habitat") val habitat: String = "",
    @ColumnInfo(name = "isBaby") val isBaby: Boolean = false,
    @ColumnInfo(name = "isLegendary") val isLegendary: Boolean = false,
    @ColumnInfo(name = "isMythical") val isMythical: Boolean = false
)