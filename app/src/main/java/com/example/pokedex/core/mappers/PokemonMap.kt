package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.PokemonEntity
import com.example.pokedex.domain.model.PokemonListModel

fun PokemonEntity.toDomain() = PokemonListModel(
    id = id,
    name = name,
    url = url,
    imageLittle = imageLittle,
    imageBig = imageBig,
    typeSlot1 = typeSlot1,
    typeSlot2 = typeSlot2
)

fun PokemonListModel.toRoom() = PokemonEntity(
    id = id,
    name = name,
    url = url,
    imageLittle = imageLittle,
    imageBig = imageBig,
    typeSlot1 = typeSlot1,
    typeSlot2 = typeSlot2
)