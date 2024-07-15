package com.example.pokedex.ui.activities.home.viewmodel

import com.example.pokedex.dispatcher.DispatcherRule
import com.example.pokedex.dispatcher.TestDispatchers
import com.example.pokedex.domain.usecase.GetPokemonListUseCase
import com.example.pokedex.domain.usecase.ReadColumnsListUseCase
import com.example.pokedex.domain.usecase.ReadDarkModeUseCase
import com.example.pokedex.domain.usecase.ReadPokemonTypeUseCase
import com.example.pokedex.domain.usecase.SaveColumnsListUseCase
import com.example.pokedex.domain.usecase.SaveDarkModeUseCase
import com.example.pokedex.domain.usecase.SavePokemonTypeUseCase
import com.example.pokedex.motherobject.PokedexMotherObject.anyColumnList
import com.example.pokedex.motherobject.PokedexMotherObject.anyDarkMode
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonListStateLoading
import com.example.pokedex.motherobject.PokedexMotherObject.anyPokemonType
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @MockK
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @MockK
    private lateinit var readDarkModeUseCase: ReadDarkModeUseCase

    @MockK
    private lateinit var saveDarkModeUseCase: SaveDarkModeUseCase

    @MockK
    private lateinit var readPokemonTypeUseCase: ReadPokemonTypeUseCase

    @MockK
    private lateinit var savePokemonTypeUseCase: SavePokemonTypeUseCase

    @MockK
    private lateinit var readColumnsListUseCase: ReadColumnsListUseCase

    @MockK
    private lateinit var saveColumnsListUseCase: SaveColumnsListUseCase

    private lateinit var mainViewModel: MainViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testDispatchers = TestDispatchers()
        mainViewModel = MainViewModel(
            testDispatchers,
            getPokemonListUseCase,
            readDarkModeUseCase,
            saveDarkModeUseCase,
            readPokemonTypeUseCase,
            savePokemonTypeUseCase,
            readColumnsListUseCase,
            saveColumnsListUseCase
        )
    }

    @Test
    fun `when MainViewModel is created successfully, it should calls readColumnsListUseCase, readDarkModeUseCase, readPokemonTypeUseCase, getPokemonListUseCase each once`() =
        runBlocking {
            //Given
            coEvery { readColumnsListUseCase.invoke() } returns anyColumnList
            coEvery { readDarkModeUseCase.invoke() } returns anyDarkMode
            coEvery { readPokemonTypeUseCase.invoke() } returns anyPokemonType
            coEvery { getPokemonListUseCase.invoke() } returns anyPokemonListStateLoading

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { readColumnsListUseCase.invoke() }
            coVerify(exactly = 1) { readDarkModeUseCase.invoke() }
            coVerify(exactly = 1) { readPokemonTypeUseCase.invoke() }
            coVerify(exactly = 1) { getPokemonListUseCase.invoke() }
        }

    @Test
    fun `when MainViewModel is created successfully, then columnList is loaded`() =
        runBlocking {
            //Given
            coEvery { readColumnsListUseCase.invoke() } returns anyColumnList

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()
            val columnList = mainViewModel.columnsList.value

            //Then
            assert(columnList == anyColumnList)
        }

    @Test
    fun `when MainViewModel is created successfully, then darkMode is loaded`() =
        runBlocking {
            //Given
            coEvery { readDarkModeUseCase.invoke() } returns anyDarkMode

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()
            val darkMode = mainViewModel.darkMode.value

            //Then
            assert(darkMode == anyDarkMode)
        }

    @Test
    fun `when MainViewModel is created successfully, then pokemonType is loaded`() =
        runBlocking {
            //Given
            coEvery { readPokemonTypeUseCase.invoke() } returns anyPokemonType

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()
            val pokemonType = mainViewModel.pokemonType.value

            //Then
            assert(pokemonType == anyPokemonType)
        }

    @Test
    fun `when MainViewModel is created successfully, then pokemonListStateLoading is loaded`() =
        runBlocking {
            //Given
            coEvery { getPokemonListUseCase.invoke() } returns anyPokemonListStateLoading

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()
            val pokemonListState = mainViewModel.pokemonListState.value

            //Then
            assert(pokemonListState == anyPokemonListStateLoading)
        }

    @Test
    fun `when MainViewModel calls saveColumnsList successfully, it should call saveColumnsListUseCase once`() =
        runBlocking {
            //Given
            val anyColumnList = anyColumnList
            coEvery { saveColumnsListUseCase.invoke(anyColumnList) } just runs

            //When
            mainViewModel.saveColumnsList(anyColumnList)
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { saveColumnsListUseCase.invoke(anyColumnList) }
        }

    @Test
    fun `when MainViewModel calls saveDarkMode successfully, it should call saveDarkModeUseCase once`() =
        runBlocking {
            //Given
            val anyDarkMode = anyDarkMode
            coEvery { saveDarkModeUseCase.invoke(anyDarkMode) } just runs

            //When
            mainViewModel.saveDarkMode(anyDarkMode)
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { saveDarkModeUseCase.invoke(anyDarkMode) }
        }

    @Test
    fun `when MainViewModel calls savePokemonType successfully, it should call savePokemonTypeUseCase once`() =
        runBlocking {
            //Given
            val anyPokemonType = anyPokemonType
            coEvery { savePokemonTypeUseCase.invoke(anyPokemonType) } just runs

            //When
            mainViewModel.savePokemonType(anyPokemonType)
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { savePokemonTypeUseCase.invoke(anyPokemonType) }
        }
}