package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonListModel
import io.kotlintest.shouldBe
import org.junit.Test

class PokemonMapKtTest {

    @Test
    fun `toRoom should return a correct PokemonEntity`() {
        //Given
        val pokemonListModel = anyPokemonListModel

        //When
        val pokemonListEntity = pokemonListModel.toRoom()

        //Then
        pokemonListEntity.id shouldBe pokemonListModel.id
        pokemonListEntity.name shouldBe pokemonListModel.name
        pokemonListEntity.url shouldBe pokemonListModel.url
        pokemonListEntity.imageLittle shouldBe pokemonListModel.imageLittle
        pokemonListEntity.imageBig shouldBe pokemonListModel.imageBig
        pokemonListEntity.typeSlot1 shouldBe pokemonListModel.typeSlot1
        pokemonListEntity.typeSlot2 shouldBe pokemonListModel.typeSlot2
    }

    @Test
    fun `toDomain should return a correct PokemonListModel`() {
        //Given
        val pokemonListEntity = anyPokemonEntity

        //When
        val pokemonListModel = pokemonListEntity.toDomain()

        //Then
        pokemonListModel.id shouldBe pokemonListEntity.id
        pokemonListModel.name shouldBe pokemonListEntity.name
        pokemonListModel.url shouldBe pokemonListEntity.url
        pokemonListModel.imageLittle shouldBe pokemonListEntity.imageLittle
        pokemonListModel.imageBig shouldBe pokemonListEntity.imageBig
        pokemonListModel.typeSlot1 shouldBe pokemonListEntity.typeSlot1
        pokemonListModel.typeSlot2 shouldBe pokemonListEntity.typeSlot2
    }
}