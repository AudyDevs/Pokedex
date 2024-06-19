package com.example.pokedex.core.mappers

import com.example.pokedex.data.room.entities.PokemonInfoEntity
import com.example.pokedex.domain.model.PokemonInfoModel

fun PokemonInfoEntity.toDomain() = PokemonInfoModel(
    id = id,
    name = name,
    abilities = abilities,
    legacyCries = legacyCries,
    latestCries = latestCries,
    baseExperience = baseExperience,
    hpStat = hpStat,
    attackStat = attackStat,
    defenseStat = defenseStat,
    specialAttackStat = specialAttackStat,
    specialDefenseStat = specialDefenseStat,
    speedStat = speedStat,
    imageLittle = imageLittle,
    imageBig = imageBig,
    image3D = image3D,
    height = height,
    weight = weight
)

fun PokemonInfoModel.toRoom() = PokemonInfoEntity(
    id = id,
    name = name,
    abilities = abilities,
    legacyCries = legacyCries,
    latestCries = latestCries,
    baseExperience = baseExperience,
    hpStat = hpStat,
    attackStat = attackStat,
    defenseStat = defenseStat,
    specialAttackStat = specialAttackStat,
    specialDefenseStat = specialDefenseStat,
    speedStat = speedStat,
    imageLittle = imageLittle,
    imageBig = imageBig,
    image3D = image3D,
    height = height,
    weight = weight
)