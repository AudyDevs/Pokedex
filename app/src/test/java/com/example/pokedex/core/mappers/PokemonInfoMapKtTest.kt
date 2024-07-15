package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonInfoEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonInfoModel
import io.kotlintest.shouldBe
import org.junit.Test

class PokemonInfoMapKtTest {

    @Test
    fun `toRoom should return a correct PokemonInfoEntity`() {
        //Given
        val pokemonInfoModel = anyPokemonInfoModel

        //When
        val pokemonInfoEntity = pokemonInfoModel.toRoom()

        //Then
        pokemonInfoEntity.id shouldBe pokemonInfoModel.id
        pokemonInfoEntity.name shouldBe pokemonInfoModel.name
        pokemonInfoEntity.abilities shouldBe pokemonInfoModel.abilities
        pokemonInfoEntity.legacyCries shouldBe pokemonInfoModel.legacyCries
        pokemonInfoEntity.latestCries shouldBe pokemonInfoModel.latestCries
        pokemonInfoEntity.baseExperience shouldBe pokemonInfoModel.baseExperience
        pokemonInfoEntity.hpStat shouldBe pokemonInfoModel.hpStat
        pokemonInfoEntity.attackStat shouldBe pokemonInfoModel.attackStat
        pokemonInfoEntity.defenseStat shouldBe pokemonInfoModel.defenseStat
        pokemonInfoEntity.specialAttackStat shouldBe pokemonInfoModel.specialAttackStat
        pokemonInfoEntity.specialDefenseStat shouldBe pokemonInfoModel.specialDefenseStat
        pokemonInfoEntity.speedStat shouldBe pokemonInfoModel.speedStat
        pokemonInfoEntity.imageLittle shouldBe pokemonInfoModel.imageLittle
        pokemonInfoEntity.imageBig shouldBe pokemonInfoModel.imageBig
        pokemonInfoEntity.image3D shouldBe pokemonInfoModel.image3D
        pokemonInfoEntity.height shouldBe pokemonInfoModel.height
        pokemonInfoEntity.weight shouldBe pokemonInfoModel.weight
    }

    @Test
    fun `toDomain should return a correct PokemonInfoModel`() {
        //Given
        val pokemonInfoEntity = anyPokemonInfoEntity

        //When
        val pokemonInfoModel = pokemonInfoEntity.toDomain()

        //Then
        pokemonInfoModel.id shouldBe pokemonInfoEntity.id
        pokemonInfoModel.name shouldBe pokemonInfoEntity.name
        pokemonInfoModel.abilities shouldBe pokemonInfoEntity.abilities
        pokemonInfoModel.legacyCries shouldBe pokemonInfoEntity.legacyCries
        pokemonInfoModel.latestCries shouldBe pokemonInfoEntity.latestCries
        pokemonInfoModel.baseExperience shouldBe pokemonInfoEntity.baseExperience
        pokemonInfoModel.hpStat shouldBe pokemonInfoEntity.hpStat
        pokemonInfoModel.attackStat shouldBe pokemonInfoEntity.attackStat
        pokemonInfoModel.defenseStat shouldBe pokemonInfoEntity.defenseStat
        pokemonInfoModel.specialAttackStat shouldBe pokemonInfoEntity.specialAttackStat
        pokemonInfoModel.specialDefenseStat shouldBe pokemonInfoEntity.specialDefenseStat
        pokemonInfoModel.speedStat shouldBe pokemonInfoEntity.speedStat
        pokemonInfoModel.imageLittle shouldBe pokemonInfoEntity.imageLittle
        pokemonInfoModel.imageBig shouldBe pokemonInfoEntity.imageBig
        pokemonInfoModel.image3D shouldBe pokemonInfoEntity.image3D
        pokemonInfoModel.height shouldBe pokemonInfoEntity.height
        pokemonInfoModel.weight shouldBe pokemonInfoEntity.weight
    }
}