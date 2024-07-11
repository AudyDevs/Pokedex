package com.example.pokedex.core.extensions

import com.example.pokedex.R

fun String.backgroundColorTypeList(): Int {
    return when (this) {
        "bug" -> R.color.background_bug
        "dark" -> R.color.background_dark
        "dragon" -> R.color.background_dragon
        "electric" -> R.color.background_electric
        "fairy" -> R.color.background_fairy
        "fighting" -> R.color.background_fighting
        "fire" -> R.color.background_fire
        "flying" -> R.color.background_flying
        "ghost" -> R.color.background_ghost
        "grass" -> R.color.background_grass
        "ground" -> R.color.background_ground
        "ice" -> R.color.background_ice
        "normal" -> R.color.background_normal
        "poison" -> R.color.background_poison
        "psychic" -> R.color.background_psychic
        "rock" -> R.color.background_rock
        "steel" -> R.color.background_steel
        "water" -> R.color.background_water
        else -> R.color.background
    }
}

fun String.backgroundColorType(): Int {
    return when (this) {
        "bug" -> R.color.bug
        "dark" -> R.color.dark
        "dragon" -> R.color.dragon
        "electric" -> R.color.electric
        "fairy" -> R.color.fairy
        "fighting" -> R.color.fighting
        "fire" -> R.color.fire
        "flying" -> R.color.flying
        "ghost" -> R.color.ghost
        "grass" -> R.color.grass
        "ground" -> R.color.ground
        "ice" -> R.color.ice
        "normal" -> R.color.normal
        "poison" -> R.color.poison
        "psychic" -> R.color.psychic
        "rock" -> R.color.rock
        "steel" -> R.color.steel
        "water" -> R.color.water
        else -> R.color.background
    }
}

fun String.iconPokemonType(): Int {
    return when (this) {
        "bug" -> R.drawable.ic_type_bug
        "dark" -> R.drawable.ic_type_dark
        "dragon" -> R.drawable.ic_type_dragon
        "electric" -> R.drawable.ic_type_electric
        "fairy" -> R.drawable.ic_type_fairy
        "fighting" -> R.drawable.ic_type_fighting
        "fire" -> R.drawable.ic_type_fire
        "flying" -> R.drawable.ic_type_flying
        "ghost" -> R.drawable.ic_type_ghost
        "grass" -> R.drawable.ic_type_grass
        "ground" -> R.drawable.ic_type_ground
        "ice" -> R.drawable.ic_type_ice
        "normal" -> R.drawable.ic_type_normal
        "poison" -> R.drawable.ic_type_poison
        "psychic" -> R.drawable.ic_type_psychic
        "rock" -> R.drawable.ic_type_rock
        "steel" -> R.drawable.ic_type_steel
        "water" -> R.drawable.ic_type_water
        else -> R.drawable.ic_pokeball
    }
}

fun String.textPokemonType(): String {
    return when (this) {
        "bug" -> "Bicho"
        "dark" -> "Oscuro"
        "dragon" -> "Dragón"
        "electric" -> "Eléctrico"
        "fairy" -> "Hada"
        "fighting" -> "Lucha"
        "fire" -> "Fuego"
        "flying" -> "Volador"
        "ghost" -> "Fantasma"
        "grass" -> "Hierba"
        "ground" -> "Tierra"
        "ice" -> "Hielo"
        "normal" -> "Normal"
        "poison" -> "Veneno"
        "psychic" -> "Psíquico"
        "rock" -> "Roca"
        "steel" -> "Acero"
        "water" -> "Agua"
        else -> ""
    }
}