package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.PokemonRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyIdPokemon
import com.example.pokedex.motherobject.PokedexMotherObject.anyNamePokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonByIDUseCaseTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var getPokemonByIDUseCase: GetPokemonByIDUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonByIDUseCase = GetPokemonByIDUseCase(pokemonRepository)
    }

    @Test
    fun `when GetPokemonByIDUseCase is called successfully, PokemonRepository should return a correct name of Pokemon`() =
        runBlocking {
            //Given
            val idPokemon = anyIdPokemon
            coEvery { pokemonRepository.getPokemonByID(idPokemon) } returns anyNamePokemon

            //When
            val namePokemon = getPokemonByIDUseCase.invoke(idPokemon)

            //Then
            assert(namePokemon == anyNamePokemon)
        }


    @Test
    fun `when GetPokemonByIDUseCase is called successfully, PokemonRepository should call getPokemonByID once`() =
        runBlocking {
            //Given
            val idPokemon = anyIdPokemon
            coEvery { pokemonRepository.getPokemonByID(idPokemon) } returns anyNamePokemon

            //When
            getPokemonByIDUseCase.invoke(idPokemon)

            //Then
            coVerify(exactly = 1) { pokemonRepository.getPokemonByID(idPokemon) }
        }
}