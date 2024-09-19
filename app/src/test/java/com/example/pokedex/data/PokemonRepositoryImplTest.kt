package com.example.pokedex.data

import com.example.pokedex.data.network.PokemonApiService
import com.example.pokedex.data.room.dao.PokemonDao
import com.example.pokedex.motherobject.PokedexMotherObject.anyIdPokemon
import com.example.pokedex.motherobject.PokedexMotherObject.anyListPokemonEntity
import com.example.pokedex.motherobject.PokedexMotherObject.anyNamePokemon
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonRepositoryImplTest {

    @MockK
    private lateinit var pokemonApiService: PokemonApiService

    @MockK
    private lateinit var pokemonDao: PokemonDao

    private lateinit var pokemonRepositoryImpl: PokemonRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        pokemonRepositoryImpl = PokemonRepositoryImpl(
            pokemonApiService,
            pokemonDao
        )
    }

    @Test
    fun `when PokemonRepositoryImpl call getPokemonList successfully, it should call getPokemon from PokemonDao once`() =
        runBlocking {
            //Given
            coEvery { pokemonDao.getPokemon() } returns anyListPokemonEntity

            //When
            pokemonRepositoryImpl.getPokemonList()

            //Then
            coVerify(exactly = 1) { pokemonDao.getPokemon() }
        }

    @Test
    fun `when PokemonRepositoryImpl call getPokemonByID successfully, it should return a correct name of Pokemon`() =
        runBlocking {
            //Given
            val anyIdPokemon = anyIdPokemon
            coEvery { pokemonDao.getPokemonByID(anyIdPokemon) } returns anyNamePokemon

            //When
            val namePokemon = pokemonRepositoryImpl.getPokemonByID(anyIdPokemon)

            //Then
            assert(namePokemon == anyNamePokemon)
        }

    @Test
    fun `when PokemonRepositoryImpl call getPokemonByID successfully, it should call getPokemonByID from PokemonDao once`() =
        runBlocking {
            //Given
            val anyIdPokemon = anyIdPokemon
            coEvery { pokemonDao.getPokemonByID(anyIdPokemon) } returns anyNamePokemon

            //When
            pokemonRepositoryImpl.getPokemonByID(anyIdPokemon)

            //Then
            coVerify(exactly = 1) { pokemonDao.getPokemonByID(anyIdPokemon) }
        }
}