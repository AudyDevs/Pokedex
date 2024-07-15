package com.example.pokedex.domain.usecase

import com.example.pokedex.domain.DataStoreRepository
import com.example.pokedex.motherobject.PokedexMotherObject.anyColumnList
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ReadColumnsListUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readColumnsListUseCase: ReadColumnsListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        readColumnsListUseCase = ReadColumnsListUseCase(dataStoreRepository)
    }

    @Test
    fun `when ReadColumnsListUseCase is called successfully, DataStoreRepository should return a correct columnsList`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readColumnsList() } returns anyColumnList

            //When
            val columnLit = readColumnsListUseCase.invoke()

            //Then
            assert(columnLit == anyColumnList)
        }

    @Test
    fun `when ReadColumnsListUseCase is called successfully, DataStoreRepository should call readColumnsList once`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readColumnsList() } returns anyColumnList

            //When
            readColumnsListUseCase.invoke()

            //Then
            coVerify(exactly = 1) { dataStoreRepository.readColumnsList() }
        }
}