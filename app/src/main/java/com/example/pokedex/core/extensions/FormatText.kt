package com.example.pokedex.core.extensions

import java.util.Locale

fun Int.formatIdPokemon(): String {
    return String.format("#%04d", this)
}

fun String.formatNamePokemon(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}

fun String.formatWeightPokemon(): String {
    return "${this.toDouble() / 10} kg"
}

fun String.formatHeightPokemon(): String {
    val number = this.toDouble() / 10
    return "%.2f m".format(number)
}