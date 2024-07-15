package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject
import com.example.pokedex.motherobject.PokedexMotherObject.anyDarkMode
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ReadDarkModeUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readDarkModeUseCase: ReadDarkModeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        readDarkModeUseCase = ReadDarkModeUseCase(dataStoreRepository)
    }

    @Test
    fun `when ReadDarkModeUseCase is called successfully, DataStoreRepository should return a correct darkMode`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readDarkMode() } returns anyDarkMode

            //When
            val darkMode = readDarkModeUseCase.invoke()

            //Then
            assert(darkMode == anyDarkMode)
        }

    @Test
    fun `when ReadDarkModeUseCase is called successfully, DataStoreRepository should call readDarkMode once`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readDarkMode() } returns anyDarkMode

            //When
            readDarkModeUseCase.invoke()

            //Then
            coVerify(exactly = 1) { dataStoreRepository.readDarkMode() }
        }
}