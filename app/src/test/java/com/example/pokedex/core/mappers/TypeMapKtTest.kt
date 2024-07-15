package com.example.pokedex.core.mappers

import com.example.pokedex.motherobject.PokedexMotherObject.anyTypeEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyTypeModel
import io.kotlintest.shouldBe
import org.junit.Test

class TypeMapKtTest{

    @Test
    fun `toRoom should return a correct TypeEntity`() {
        //Given
        val typeModel = anyTypeModel

        //When
        val typeEntity = typeModel.toRoom()

        //Then
        typeEntity.id shouldBe typeModel.id
        typeEntity.name shouldBe typeModel.name
        typeEntity.typeSlot shouldBe typeModel.typeSlot
        typeEntity.namePokemon shouldBe typeModel.namePokemon
        typeEntity.doubleDamageFrom shouldBe typeModel.doubleDamageFrom
        typeEntity.doubleDamageTo shouldBe typeModel.doubleDamageTo
        typeEntity.halfDamageFrom shouldBe typeModel.halfDamageFrom
        typeEntity.halfDamageTo shouldBe typeModel.halfDamageTo
        typeEntity.noDamageFrom shouldBe typeModel.noDamageFrom
        typeEntity.noDamageTo shouldBe typeModel.noDamageTo
    }

    @Test
    fun `toDomain should return a correct TypeModel`() {
        //Given
        val typeEntity = anyTypeEntity

        //When
        val typeModel = typeEntity.toDomain()

        //Then
        typeModel.id shouldBe typeEntity.id
        typeModel.name shouldBe typeEntity.name
        typeModel.typeSlot shouldBe typeEntity.typeSlot
        typeModel.namePokemon shouldBe typeEntity.namePokemon
        typeModel.doubleDamageFrom shouldBe typeEntity.doubleDamageFrom
        typeModel.doubleDamageTo shouldBe typeEntity.doubleDamageTo
        typeModel.halfDamageFrom shouldBe typeEntity.halfDamageFrom
        typeModel.halfDamageTo shouldBe typeEntity.halfDamageTo
        typeModel.noDamageFrom shouldBe typeEntity.noDamageFrom
        typeModel.noDamageTo shouldBe typeEntity.noDamageTo
    }
}