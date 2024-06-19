package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.TypeEntity
import com.example.pokedex.domain.model.TypeModel

fun TypeEntity.toDomain() = TypeModel(
    id = id,
    name = name,
    typeSlot = typeSlot,
    namePokemon = namePokemon,
    doubleDamageFrom = doubleDamageFrom,
    doubleDamageTo = doubleDamageTo,
    halfDamageFrom = halfDamageFrom,
    halfDamageTo = halfDamageTo,
    noDamageFrom = noDamageFrom,
    noDamageTo = noDamageTo
)

fun TypeModel.toRoom() = TypeEntity(
    id = id,
    name = name,
    typeSlot = typeSlot,
    namePokemon = namePokemon,
    doubleDamageFrom = doubleDamageFrom,
    doubleDamageTo = doubleDamageTo,
    halfDamageFrom = halfDamageFrom,
    halfDamageTo = halfDamageTo,
    noDamageFrom = noDamageFrom,
    noDamageTo = noDamageTo
)