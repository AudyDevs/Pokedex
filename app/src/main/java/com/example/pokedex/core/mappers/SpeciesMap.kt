package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.SpeciesEntity
import com.example.pokedex.domain.model.SpeciesModel

fun SpeciesEntity.toDomain() = SpeciesModel(
    id = id,
    name = name,
    baseHappiness = baseHappiness,
    captureRate = captureRate,
    idEvolutionChain = idEvolutionChain,
    description = description,
    pokemonClass = pokemonClass,
    habitat = habitat,
    isBaby = isBaby,
    isLegendary = isLegendary,
    isMythical = isMythical
)

fun SpeciesModel.toRoom() = SpeciesEntity(
    id = id,
    name = name,
    baseHappiness = baseHappiness,
    captureRate = captureRate,
    idEvolutionChain = idEvolutionChain,
    description = description,
    pokemonClass = pokemonClass,
    habitat = habitat,
    isBaby = isBaby,
    isLegendary = isLegendary,
    isMythical = isMythical
)