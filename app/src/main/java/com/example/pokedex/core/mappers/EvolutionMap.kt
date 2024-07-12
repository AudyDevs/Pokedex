package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.EvolutionEntity
import com.example.pokedex.domain.model.EvolutionModel

fun EvolutionEntity.toDomain() = EvolutionModel(
    id = id,
    idEvolution = idEvolution,
    idPokemonOrigin = idPokemonOrigin,
    namePokemon = namePokemon,
    idPokemonEvolution = idPokemonEvolution,
    nameEvolution = nameEvolution,
    itemEvolution = itemEvolution,
    minLevel = minLevel,
    trigger = trigger,
    happiness = happiness,
    timeOfDay = timeOfDay,
    location = location
)

fun EvolutionModel.toRoom() = EvolutionEntity(
    id = id,
    idEvolution = idEvolution,
    idPokemonOrigin = idPokemonOrigin,
    namePokemon = namePokemon,
    idPokemonEvolution = idPokemonEvolution,
    nameEvolution = nameEvolution,
    itemEvolution = itemEvolution,
    minLevel = minLevel,
    trigger = trigger,
    happiness = happiness,
    timeOfDay = timeOfDay,
    location = location
)