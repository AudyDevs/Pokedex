package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.PokemonRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyNamePokemon
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonInfoStateLoading
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonInfoUseCaseTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var getPokemonInfoUseCase: GetPokemonInfoUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonInfoUseCase = GetPokemonInfoUseCase(pokemonRepository)
    }

    @Test
    fun `when GetPokemonInfoUseCase is called successfully, PokemonRepository should return a correct PokemonInfoState`() =
        runBlocking {
            //Given
            val anyPokemonInfoStateLoading = anyPokemonInfoStateLoading
            val namePokemon = anyNamePokemon
            coEvery { pokemonRepository.getPokemonInfo(namePokemon) } returns anyPokemonInfoStateLoading

            //When
            val pokemonInfoStateLoading = getPokemonInfoUseCase.invoke(namePokemon)

            //Then
            assert(pokemonInfoStateLoading == anyPokemonInfoStateLoading)
        }

    @Test
    fun `when GetPokemonInfoUseCase is called successfully, PokemonRepository should call getPokemonInfo once`() =
        runBlocking {
            //Given
            val anyPokemonInfoStateLoading = anyPokemonInfoStateLoading
            val namePokemon = anyNamePokemon
            coEvery { pokemonRepository.getPokemonInfo(namePokemon) } returns anyPokemonInfoStateLoading

            //When
            getPokemonInfoUseCase.invoke(namePokemon)

            //Then
            coVerify(exactly = 1) { pokemonRepository.getPokemonInfo(namePokemon) }
        }
}