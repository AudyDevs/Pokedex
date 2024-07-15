package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anyAbilityEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyAbilityModel
import io.kotlintest.shouldBe
import org.junit.Test

class AbilityMapKtTest {

    @Test
    fun `toRoom should return a correct AbilityEntity`() {
        //Given
        val abilityModel = anyAbilityModel

        //When
        val abilityEntity = abilityModel.toRoom()

        //Then
        abilityEntity.id shouldBe abilityModel.id
        abilityEntity.name shouldBe abilityModel.name
        abilityEntity.description shouldBe abilityModel.description
    }

    @Test
    fun `toDomain should return a correct AbilityModel`() {
        //Given
        val abilityEntity = anyAbilityEntity

        //When
        val abilityModel = abilityEntity.toDomain()

        //Then
        abilityModel.id shouldBe abilityEntity.id
        abilityModel.name shouldBe abilityEntity.name
        abilityModel.description shouldBe abilityEntity.description
    }
}