package com.example.pokedex.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedex.data.room.converter.ListConverter

@Entity(tableName = "type")
@TypeConverters(ListConverter::class)
data class TypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "typeSlot") val typeSlot: Int = 0,
    @ColumnInfo(name = "namePokemon") val namePokemon: String = "",
    @ColumnInfo(name = "doubleDamageFrom") val doubleDamageFrom: List<String> = emptyList(),
    @ColumnInfo(name = "doubleDamageTo") val doubleDamageTo: List<String> = emptyList(),
    @ColumnInfo(name = "halfDamageFrom") val halfDamageFrom: List<String> = emptyList(),
    @ColumnInfo(name = "halfDamageTo") val halfDamageTo: List<String> = emptyList(),
    @ColumnInfo(name = "noDamageFrom") val noDamageFrom: List<String> = emptyList(),
    @ColumnInfo(name = "noDamageTo") val noDamageTo: List<String> = emptyList()
)