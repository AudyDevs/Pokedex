package com.example.pokedex.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedex.data.room.converter.ListConverter

@Entity(tableName = "pokemonInfo")
@TypeConverters(ListConverter::class)
data class PokemonInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "abilities") val abilities: List<String> = emptyList(),
    @ColumnInfo(name = "legacyCries") val legacyCries: String = "",
    @ColumnInfo(name = "latestCries") val latestCries: String = "",
    @ColumnInfo(name = "baseExperience") val baseExperience: Int = 0,
    @ColumnInfo(name = "hpStat") val hpStat: Int = 0,
    @ColumnInfo(name = "attackStat") val attackStat: Int = 0,
    @ColumnInfo(name = "defenseStat") val defenseStat: Int = 0,
    @ColumnInfo(name = "specialAttackStat") val specialAttackStat: Int = 0,
    @ColumnInfo(name = "specialDefenseStat") val specialDefenseStat: Int = 0,
    @ColumnInfo(name = "speedStat") val speedStat: Int = 0,
    @ColumnInfo(name = "imageLittle") val imageLittle: String = "",
    @ColumnInfo(name = "imageBig") val imageBig: String = "",
    @ColumnInfo(name = "image3D") val image3D: String = "",
    @ColumnInfo(name = "height") val height: Int = 0,
    @ColumnInfo(name = "weight") val weight: Int = 0
)