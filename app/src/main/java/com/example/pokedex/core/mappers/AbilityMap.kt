package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.AbilityEntity
import com.example.pokedex.domain.model.AbilityModel

fun AbilityEntity.toDomain() = AbilityModel(
    id = id,
    name = name,
    description = description
)

fun AbilityModel.toRoom() = AbilityEntity(
    id = id,
    name = name,
    description = description
)