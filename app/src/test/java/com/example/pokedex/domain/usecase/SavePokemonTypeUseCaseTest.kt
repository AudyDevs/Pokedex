package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonType
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SavePokemonTypeUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var savePokemonTypeUseCase: SavePokemonTypeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        savePokemonTypeUseCase = SavePokemonTypeUseCase(dataStoreRepository)
    }

    @Test
    fun `when SavePokemonTypeUseCase is called successfully, DataStoreRepository should call savePokemonType once`() =
        runBlocking {
            //Given
            val anyPokemonType = anyPokemonType
            coEvery { dataStoreRepository.savePokemonType(anyPokemonType) } just runs

            //When
            savePokemonTypeUseCase.invoke(anyPokemonType)

            //Then
            coVerify(exactly = 1) { dataStoreRepository.savePokemonType(anyPokemonType) }
        }
}