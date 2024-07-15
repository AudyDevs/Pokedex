package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.PokemonRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonListStateLoading
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonListUseCase = GetPokemonListUseCase(pokemonRepository)
    }

    @Test
    fun `when GetPokemonListUseCase is called successfully, PokemonRepository should return a correct PokemonListState`() =
        runBlocking {
            //Given
            val anyPokemonListStateLoading = anyPokemonListStateLoading
            coEvery { pokemonRepository.getPokemonList() } returns anyPokemonListStateLoading

            //When
            val pokemonListStateLoading = getPokemonListUseCase.invoke()

            //Then
            assert(pokemonListStateLoading == anyPokemonListStateLoading)
        }

    @Test
    fun `when GetPokemonListUseCase is called successfully, PokemonRepository should call getPokemonList once`() =
        runBlocking {
            //Given
            val anyPokemonListStateLoading = anyPokemonListStateLoading
            coEvery { pokemonRepository.getPokemonList() } returns anyPokemonListStateLoading

            //When
            getPokemonListUseCase.invoke()

            //Then
            coVerify(exactly = 1) { pokemonRepository.getPokemonList() }
        }
}