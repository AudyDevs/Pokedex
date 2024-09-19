package com.example.pokedex.data

import com.example.pokedex.core.datastore.DataStorePreferences
import com.example.pokedex.motherobject.PokedexMotherObject.anyColumnList
import com.example.pokedex.motherobject.PokedexMotherObject.anyDarkMode
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

class DataStoreRepositoryImplTest {

    @MockK
    private lateinit var dataStorePreferences: DataStorePreferences
    private lateinit var dataStoreRepositoryImpl: DataStoreRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataStoreRepositoryImpl = DataStoreRepositoryImpl(dataStorePreferences)
    }

    @Test
    fun `when DataStoreRepositoryImpl call readDarkMode successfully, it should return a correct DarkMode`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStoreDarkMode() } returns anyDarkMode

            //When
            val darkMode = dataStoreRepositoryImpl.readDarkMode()

            //Then
            assert(darkMode == anyDarkMode)
        }

    @Test
    fun `when DataStoreRepositoryImpl call readDarkMode successfully, it should call readDataStoreDarkMode from DataStorePreferences once`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStoreDarkMode() } returns anyDarkMode

            //When
            dataStoreRepositoryImpl.readDarkMode()

            //Then
            coVerify(exactly = 1) { dataStorePreferences.readDataStoreDarkMode() }
        }

    @Test
    fun `when DataStoreRepositoryImpl call saveDarkMode successfully, it should call saveDataStoreDarkMode from DataStorePreferences once`() =
        runBlocking {
            //Given
            val anyDarkMode = anyDarkMode
            coEvery { dataStorePreferences.saveDataStoreDarkMode(anyDarkMode) } just runs

            //When
            dataStoreRepositoryImpl.saveDarkMode(anyDarkMode)

            //Then
            coVerify(exactly = 1) { dataStorePreferences.saveDataStoreDarkMode(anyDarkMode) }
        }

    @Test
    fun `when DataStoreRepositoryImpl call readPokemonType successfully, it should return a correct PokemonType`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStorePokemonType() } returns anyPokemonType

            //When
            val pokemonType = dataStoreRepositoryImpl.readPokemonType()

            //Then
            assert(pokemonType == anyPokemonType)
        }

    @Test
    fun `when DataStoreRepositoryImpl call readPokemonType successfully, it should call readDataStorePokemonType from DataStorePreferences once`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStorePokemonType() } returns anyPokemonType

            //When
            dataStoreRepositoryImpl.readPokemonType()

            //Then
            coVerify(exactly = 1) { dataStorePreferences.readDataStorePokemonType() }
        }

    @Test
    fun `when DataStoreRepositoryImpl call savePokemonType successfully, it should call saveDataStorePokemonType from DataStorePreferences once`() =
        runBlocking {
            //Given
            val anyPokemonType = anyPokemonType
            coEvery { dataStorePreferences.saveDataStorePokemonType(anyPokemonType) } just runs

            //When
            dataStoreRepositoryImpl.savePokemonType(anyPokemonType)

            //Then
            coVerify(exactly = 1) { dataStorePreferences.saveDataStorePokemonType(anyPokemonType) }
        }

    @Test
    fun `when DataStoreRepositoryImpl call readColumnsList successfully, it should return a correct ColumnList`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStoreColumnsList() } returns anyColumnList

            //When
            val columnList = dataStoreRepositoryImpl.readColumnsList()

            //Then
            assert(columnList == anyColumnList)
        }

    @Test
    fun `when DataStoreRepositoryImpl call readColumnsList successfully, it should call readDataStoreColumnsList from DataStorePreferences once`() =
        runBlocking {
            //Given
            coEvery { dataStorePreferences.readDataStoreColumnsList() } returns anyColumnList

            //When
            dataStoreRepositoryImpl.readColumnsList()

            //Then
            coVerify(exactly = 1) { dataStorePreferences.readDataStoreColumnsList() }
        }

    @Test
    fun `when DataStoreRepositoryImpl call saveColumnsList successfully, it should call saveDataStoreColumnsList from DataStorePreferences once`() =
        runBlocking {
            //Given
            val anyColumnList = anyColumnList
            coEvery { dataStorePreferences.saveDataStoreColumnsList(anyColumnList) } just runs

            //When
            dataStoreRepositoryImpl.saveColumnsList(anyColumnList)

            //Then
            coVerify(exactly = 1) { dataStorePreferences.saveDataStoreColumnsList(anyColumnList) }
        }
}