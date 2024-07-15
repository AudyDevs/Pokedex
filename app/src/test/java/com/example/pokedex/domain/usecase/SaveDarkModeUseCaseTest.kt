package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyDarkMode
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveDarkModeUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var saveDarkModeUseCase: SaveDarkModeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveDarkModeUseCase = SaveDarkModeUseCase(dataStoreRepository)
    }

    @Test
    fun `when SaveDarkModeUseCase is called successfully, DataStoreRepository should call saveDarkMode once`() =
        runBlocking {
            //Given
            val anyDarkMode = anyDarkMode
            coEvery { dataStoreRepository.saveDarkMode(anyDarkMode) } just runs

            //When
            saveDarkModeUseCase.invoke(anyDarkMode)

            //Then
            coVerify(exactly = 1) { dataStoreRepository.saveDarkMode(anyDarkMode) }
        }
}