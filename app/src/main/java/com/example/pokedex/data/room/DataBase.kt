package com.example.pokedex.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.room.dao.PokemonDao
import com.example.pokedex.data.room.entities.AbilityEntity
import com.example.pokedex.data.room.entities.PokemonEntity
import com.example.pokedex.data.room.entities.PokemonInfoEntity
import com.example.pokedex.data.room.entities.SpeciesEntity
import com.example.pokedex.data.room.entities.TypeEntity

@Database(
    entities = [PokemonEntity::class, TypeEntity::class, PokemonInfoEntity::class, AbilityEntity::class, SpeciesEntity::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun loadPokemonDao(): PokemonDao
}