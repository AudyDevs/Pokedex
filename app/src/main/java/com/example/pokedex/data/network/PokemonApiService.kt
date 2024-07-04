package com.example.pokedex.data.network

import com.example.pokedex.data.network.response.AbilityResponse
import com.example.pokedex.data.network.response.EvolutionResponse
import com.example.pokedex.data.network.response.ListResponse
import com.example.pokedex.data.network.response.PokemonResponse
import com.example.pokedex.data.network.response.SpeciesResponse
import com.example.pokedex.data.network.response.TypeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListResponse

    @GET("type")
    suspend fun getTypeList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String,
    ): PokemonResponse

    @GET("type/{type}")
    suspend fun getType(
        @Path("type") type: String,
    ): TypeResponse

    @GET("ability/{ability}")
    suspend fun getAbility(
        @Path("ability") ability: String,
    ): AbilityResponse

    @GET("pokemon-species/{species}")
    suspend fun getSpecies(
        @Path("species") species: String,
    ): SpeciesResponse


    @GET("evolution-chain/{idEvolution}")
    suspend fun getEvolution(
        @Path("idEvolution") idEvolution: String,
    ): EvolutionResponse
}