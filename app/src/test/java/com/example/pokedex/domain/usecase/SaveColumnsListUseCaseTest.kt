package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyColumnList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveColumnsListUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var saveColumnsListUseCase: SaveColumnsListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveColumnsListUseCase = SaveColumnsListUseCase(dataStoreRepository)
    }

    @Test
    fun `when SaveColumnsListUseCase is called successfully, DataStoreRepository should call saveColumnsList once`() =
        runBlocking {
            //Given
            val anyColumnList = anyColumnList
            coEvery { dataStoreRepository.saveColumnsList(anyColumnList) } just runs

            //When
            saveColumnsListUseCase.invoke(anyColumnList)

            //Then
            coVerify(exactly = 1) { dataStoreRepository.saveColumnsList(anyColumnList) }
        }
}