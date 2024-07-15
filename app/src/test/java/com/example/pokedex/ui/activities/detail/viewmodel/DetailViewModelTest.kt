package com.example.pokedex.ui.activities.detail.viewmodel

import com.example.pokedex.dispatcher.DispatcherRule
import com.example.pokedex.dispatcher.TestDispatchers
import com.example.pokedex.domain.usecase.GetPokemonByIDUseCase
import com.example.pokedex.domain.usecase.GetPokemonInfoUseCase
import com.example.pokedex.motherobject.PokedexMotherObject.anyIdPokemon
import com.example.pokedex.motherobject.PokedexMotherObject.anyNamePokemon
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonInfoStateLoading
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @MockK
    private lateinit var getPokemonInfoUseCase: GetPokemonInfoUseCase

    @MockK
    private lateinit var getPokemonByIDUseCase: GetPokemonByIDUseCase

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testDispatchers = TestDispatchers()
        detailViewModel = DetailViewModel(
            testDispatchers,
            getPokemonInfoUseCase,
            getPokemonByIDUseCase
        )
    }

    @Test
    fun `when DetailViewModel calls getPokemonInfo successfully, it should call getPokemonInfoUseCase once`() =
        runBlocking {
            //Given
            val anyNamePokemon = anyNamePokemon
            coEvery { getPokemonInfoUseCase.invoke(anyNamePokemon) } returns anyPokemonInfoStateLoading

            //When
            detailViewModel.namePokemon = anyNamePokemon
            detailViewModel.getPokemonInfo()
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { getPokemonInfoUseCase.invoke(anyNamePokemon) }
        }

    @Test
    fun `when DetailViewModel calls getPokemonInfoByID successfully, it should call getPokemonByIDUseCase once`() =
        runBlocking {
            //Given
            val anyIdPokemon = anyIdPokemon
            coEvery { getPokemonByIDUseCase.invoke(anyIdPokemon) } returns anyNamePokemon

            //When
            detailViewModel.pokemonID = anyIdPokemon
            detailViewModel.getPokemonInfoByID()
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { getPokemonByIDUseCase.invoke(anyIdPokemon) }
        }
}
