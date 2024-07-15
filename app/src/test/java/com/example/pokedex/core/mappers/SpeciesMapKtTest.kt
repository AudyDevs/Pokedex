package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anySpeciesEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anySpeciesModel
import io.kotlintest.shouldBe
import org.junit.Test

class SpeciesMapKtTest {

    @Test
    fun `toRoom should return a correct SpeciesEntity`() {
        //Given
        val speciesModel = anySpeciesModel

        //When
        val speciesEntity = speciesModel.toRoom()

        //Then
        speciesEntity.id shouldBe speciesModel.id
        speciesEntity.name shouldBe speciesModel.name
        speciesEntity.baseHappiness shouldBe speciesModel.baseHappiness
        speciesEntity.captureRate shouldBe speciesModel.captureRate
        speciesEntity.idEvolutionChain shouldBe speciesModel.idEvolutionChain
        speciesEntity.description shouldBe speciesModel.description
        speciesEntity.pokemonClass shouldBe speciesModel.pokemonClass
        speciesEntity.habitat shouldBe speciesModel.habitat
        speciesEntity.isBaby shouldBe speciesModel.isBaby
        speciesEntity.isLegendary shouldBe speciesModel.isLegendary
        speciesEntity.isMythical shouldBe speciesModel.isMythical
    }

    @Test
    fun `toDomain should return a correct SpeciesModel`() {
        //Given
        val speciesEntity = anySpeciesEntity

        //When
        val speciesModel = speciesEntity.toDomain()

        //Then
        speciesModel.id shouldBe speciesEntity.id
        speciesModel.name shouldBe speciesEntity.name
        speciesModel.baseHappiness shouldBe speciesEntity.baseHappiness
        speciesModel.captureRate shouldBe speciesEntity.captureRate
        speciesModel.idEvolutionChain shouldBe speciesEntity.idEvolutionChain
        speciesModel.description shouldBe speciesEntity.description
        speciesModel.pokemonClass shouldBe speciesEntity.pokemonClass
        speciesModel.habitat shouldBe speciesEntity.habitat
        speciesModel.isBaby shouldBe speciesEntity.isBaby
        speciesModel.isLegendary shouldBe speciesEntity.isLegendary
        speciesModel.isMythical shouldBe speciesEntity.isMythical
    }
}