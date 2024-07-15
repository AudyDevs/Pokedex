package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anyEvolutionEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyEvolutionModel
import io.kotlintest.shouldBe
import org.junit.Test

class EvolutionMapKtTest {

    @Test
    fun `toRoom should return a correct EvolutionEntity`() {
        //Given
        val evolutionModel = anyEvolutionModel

        //When
        val evolutionEntity = evolutionModel.toRoom()

        //Then
        evolutionEntity.id shouldBe evolutionModel.id
        evolutionEntity.idEvolution shouldBe evolutionModel.idEvolution
        evolutionEntity.idPokemonOrigin shouldBe evolutionModel.idPokemonOrigin
        evolutionEntity.namePokemon shouldBe evolutionModel.namePokemon
        evolutionEntity.idPokemonEvolution shouldBe evolutionModel.idPokemonEvolution
        evolutionEntity.nameEvolution shouldBe evolutionModel.nameEvolution
        evolutionEntity.itemEvolution shouldBe evolutionModel.itemEvolution
        evolutionEntity.minLevel shouldBe evolutionModel.minLevel
        evolutionEntity.trigger shouldBe evolutionModel.trigger
        evolutionEntity.happiness shouldBe evolutionModel.happiness
        evolutionEntity.timeOfDay shouldBe evolutionModel.timeOfDay
        evolutionEntity.location shouldBe evolutionModel.location
    }

    @Test
    fun `toDomain should return a correct EvolutionModel`() {
        //Given
        val evolutionEntity = anyEvolutionEntity

        //When
        val evolutionModel = evolutionEntity.toDomain()

        //Then
        evolutionModel.id shouldBe evolutionEntity.id
        evolutionModel.idEvolution shouldBe evolutionEntity.idEvolution
        evolutionModel.idPokemonOrigin shouldBe evolutionEntity.idPokemonOrigin
        evolutionModel.namePokemon shouldBe evolutionEntity.namePokemon
        evolutionModel.idPokemonEvolution shouldBe evolutionEntity.idPokemonEvolution
        evolutionModel.nameEvolution shouldBe evolutionEntity.nameEvolution
        evolutionModel.itemEvolution shouldBe evolutionEntity.itemEvolution
        evolutionModel.minLevel shouldBe evolutionEntity.minLevel
        evolutionModel.trigger shouldBe evolutionEntity.trigger
        evolutionModel.happiness shouldBe evolutionEntity.happiness
        evolutionModel.timeOfDay shouldBe evolutionEntity.timeOfDay
        evolutionModel.location shouldBe evolutionEntity.location
    }
}