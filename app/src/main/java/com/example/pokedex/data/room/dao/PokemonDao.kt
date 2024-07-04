package com.example.pokedex.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.data.room.entities.AbilityEntity
import com.example.pokedex.data.room.entities.EvolutionEntity
import com.example.pokedex.data.room.entities.PokemonEntity
import com.example.pokedex.data.room.entities.PokemonInfoEntity
import com.example.pokedex.data.room.entities.SpeciesEntity
import com.example.pokedex.data.room.entities.TypeEntity

@Dao
interface PokemonDao {

    // Pokemon
    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    suspend fun getPokemon(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonList: List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun clearPokemon()

    //PokemonInfo
    @Query("SELECT * FROM pokemonInfo WHERE name = :namePokemon")
    suspend fun getPokemonInfo(namePokemon: String): PokemonInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfoList: PokemonInfoEntity)

    @Query("DELETE FROM pokemonInfo")
    suspend fun clearPokemonInfo()

    // Type
    @Query("SELECT * FROM type WHERE name = :namePokemon")
    suspend fun getType(namePokemon: String): List<TypeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(typeList: List<TypeEntity>)

    @Query("DELETE FROM type")
    suspend fun clearType()

    // Ability
    @Query("SELECT * FROM ability WHERE name = :nameAbility")
    suspend fun getAbility(nameAbility: String): AbilityEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(abilityList: List<AbilityEntity>)

    @Query("DELETE FROM ability")
    suspend fun clearAbility()

    // Species
    @Query("SELECT * FROM species WHERE name = :namePokemon")
    suspend fun getSpecies(namePokemon: String): SpeciesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(speciesList: SpeciesEntity)

    @Query("DELETE FROM species")
    suspend fun clearSpecies()

    // Evolution
    @Query("SELECT * FROM evolution WHERE idEvolution = :idEvolutionChain")
    suspend fun getEvolution(idEvolutionChain: String): List<EvolutionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvolution(evolutionList: List<EvolutionEntity>)

    @Query("DELETE FROM evolution")
    suspend fun clearEvolution()
}