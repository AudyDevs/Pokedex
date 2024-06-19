package com.example.pokedex.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "imageLittle") val imageLittle: String = "",
    @ColumnInfo(name = "imageBig") val imageBig: String = "",
    @ColumnInfo(name = "typeSlot1") val typeSlot1: String = "",
    @ColumnInfo(name = "typeSlot2") val typeSlot2: String = ""
)