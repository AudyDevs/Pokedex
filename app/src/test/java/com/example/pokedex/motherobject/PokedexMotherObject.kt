package com.example.pokedex.motherobject

import com.example.pokedex.data.room.entities.AbilityEntity
import com.example.pokedex.data.room.entities.EvolutionEntity
import com.example.pokedex.data.room.entities.PokemonEntity
import com.example.pokedex.data.room.entities.PokemonInfoEntity
import com.example.pokedex.data.room.entities.SpeciesEntity
import com.example.pokedex.data.room.entities.TypeEntity
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.EvolutionModel
import com.example.pokedex.domain.model.PokemonInfoModel
import com.example.pokedex.domain.model.PokemonListModel
import com.example.pokedex.domain.model.SpeciesModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.domain.state.PokemonInfoState
import com.example.pokedex.domain.state.PokemonListState

object PokedexMotherObject {

    val anyAbilityModel = AbilityModel(1, "AbilityNameTest", "AbilityDescriptionTest")
    val anyAbilityEntity = AbilityEntity(1, "AbilityNameTest", "AbilityDescriptionTest")

    val anyEvolutionModel =
        EvolutionModel(
            1,
            "1",
            "1",
            "NamePokemonTest",
            "2",
            "NamePokemonEvoTest",
            "item",
            30,
            "level-up",
            50,
            "day",
            "beach"
        )
    val anyEvolutionEntity = EvolutionEntity(
        1,
        "1",
        "1",
        "NamePokemonTest",
        "2",
        "NamePokemonEvoTest",
        "item",
        30,
        "level-up",
        50,
        "day",
        "beach"
    )

    val anyPokemonInfoModel = PokemonInfoModel(
        1,
        "PokemonNameTest",
        listOf("ability1"),
        "LegacyCriesTest",
        "LatestCriesTest",
        50,
        120,
        30,
        40,
        50,
        60,
        70,
        "ImageLittleTest",
        "ImageBigTest",
        "Image3DTest",
        100,
        200
    )
    val anyPokemonInfoEntity = PokemonInfoEntity(
        1,
        "PokemonNameTest",
        listOf("ability1"),
        "LegacyCriesTest",
        "LatestCriesTest",
        50,
        120,
        30,
        40,
        50,
        60,
        70,
        "ImageLittleTest",
        "ImageBigTest",
        "Image3DTest",
        100,
        200
    )
    val anyPokemonListModel = PokemonListModel(
        1,
        "PokemonNameTest",
        "UrlTest",
        "ImageLittleTest",
        "ImageBigTest",
        "TypeSlot1Test",
        "TypeSlot2Test"
    )
    val anyPokemonEntity = PokemonEntity(
        1,
        "PokemonNameTest",
        "UrlTest",
        "ImageLittleTest",
        "ImageBigTest",
        "TypeSlot1Test",
        "TypeSlot2Test"
    )
    val anyListPokemonEntity = listOf(anyPokemonEntity, anyPokemonEntity)
    val anySpeciesModel = SpeciesModel(
        1,
        "SpeciesNameTest",
        50,
        50,
        "2",
        "DescriptionTest",
        "ClassTest",
        "HabitatTest",
        isBaby = false,
        isLegendary = false,
        isMythical = false
    )
    val anySpeciesEntity = SpeciesEntity(
        1,
        "SpeciesNameTest",
        50,
        50,
        "2",
        "DescriptionTest",
        "ClassTest",
        "HabitatTest",
        isBaby = false,
        isLegendary = false,
        isMythical = false
    )
    val anyTypeModel = TypeModel(
        1,
        "TypeNameTest",
        1,
        "PokemonNameTest",
        listOf("water"),
        listOf("fire"),
        listOf("dragon"),
        listOf("dark"),
        listOf("normal"),
        listOf("fairy")
    )
    val anyTypeEntity = TypeEntity(
        1,
        "TypeNameTest",
        1,
        "PokemonNameTest",
        listOf("water"),
        listOf("fire"),
        listOf("dragon"),
        listOf("dark"),
        listOf("normal"),
        listOf("fairy")
    )
    val anyIdPokemon = 1
    val anyNamePokemon = "bulbasaur"
    val anyPokemonInfoStateLoading = PokemonInfoState.Loading
    val anyPokemonListStateLoading = PokemonListState.Loading

    val anyColumnList = 1
    val anyDarkMode = false
    val anyPokemonType = "fire"
}