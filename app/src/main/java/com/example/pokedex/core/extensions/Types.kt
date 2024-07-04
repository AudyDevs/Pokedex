package com.example.pokedex.core.extensions

import com.example.pokedex.R

fun String.backgroundColorType(): Int {
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