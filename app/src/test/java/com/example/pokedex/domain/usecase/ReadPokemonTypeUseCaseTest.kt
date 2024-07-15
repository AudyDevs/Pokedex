package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonType
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ReadPokemonTypeUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readPokemonTypeUseCase: ReadPokemonTypeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        readPokemonTypeUseCase = ReadPokemonTypeUseCase(dataStoreRepository)
    }

    @Test
    fun `when ReadPokemonTypeUseCase is called successfully, DataStoreRepository should return a correct pokemonType`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readPokemonType() } returns anyPokemonType

            //When
            val pokemonType = readPokemonTypeUseCase.invoke()

            //Then
            assert(pokemonType == anyPokemonType)
        }

    @Test
    fun `when ReadPokemonTypeUseCase is called successfully, DataStoreRepository should call readPokemonType once`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readPokemonType() } returns anyPokemonType

            //When
            readPokemonTypeUseCase.invoke()

            //Then
            coVerify(exactly = 1) { dataStoreRepository.readPokemonType() }
        }
}