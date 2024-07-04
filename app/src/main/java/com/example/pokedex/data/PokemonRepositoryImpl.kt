package com.example.pokedex.data

import com.example.pokedex.core.mappers.toDomain
import com.example.pokedex.core.mappers.toRoom
import com.example.pokedex.data.network.PokemonApiService
import com.example.pokedex.data.room.dao.PokemonDao
import com.example.pokedex.data.room.entities.AbilityEntity
import com.example.pokedex.di.Constants.BIG_IMAGE
import com.example.pokedex.di.Constants.DDD_IMAGE
import com.example.pokedex.di.Constants.LIMIT_POKEMON_LIST
import com.example.pokedex.di.Constants.LITTLE_IMAGE
import com.example.pokedex.di.Constants.POKEMON_LIMIT
import com.example.pokedex.di.Constants.POKEMON_OFFSET
import com.example.pokedex.domain.PokemonRepository
import com.example.pokedex.domain.model.AbilityModel
import com.example.pokedex.domain.model.EvolutionModel
import com.example.pokedex.domain.model.PokemonInfoModel
import com.example.pokedex.domain.model.PokemonListModel
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.model.SpeciesModel
import com.example.pokedex.domain.model.TypeModel
import com.example.pokedex.domain.state.PokemonInfoState
import com.example.pokedex.domain.state.PokemonListState
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService, private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(): PokemonListState {
        val roomResponse = pokemonDao.getPokemon()
        if (roomResponse.isNotEmpty()) {
            return PokemonListState.Success(roomResponse.map { it.toDomain() })
        }
        val apiPokemonResponse = getAPIPokemonList()
        if (apiPokemonResponse is PokemonListState.Success) {
            val apiTypesResponse = getAPITypeList()
            //put type in apiPokemonResponse inside the Slot 1 and 2.
            apiPokemonResponse.pokemon.forEachIndexed { index, pokemon ->
                apiTypesResponse.filter { it.namePokemon == pokemon.name }.forEach {
                    when (it.typeSlot) {
                        1 -> apiPokemonResponse.pokemon[index].typeSlot1 = it.name
                        2 -> apiPokemonResponse.pokemon[index].typeSlot2 = it.name
                    }
                }
            }
            apiPokemonResponse.pokemon = apiPokemonResponse.pokemon.filter { it.id <= LIMIT_POKEMON_LIST }
            //Clear and save again the values.
            pokemonDao.clearPokemon()
            pokemonDao.clearType()
            pokemonDao.insertPokemon(apiPokemonResponse.pokemon.map { it.toRoom() })
            pokemonDao.insertType(apiTypesResponse.map { it.toRoom() })
        }
        return apiPokemonResponse
    }

    private suspend fun getAPIPokemonList(): PokemonListState {
        runCatching {
            pokemonApiService.getPokemonList(POKEMON_LIMIT, POKEMON_OFFSET)
        }.onSuccess { pokemonListResponse ->
            val apiPokemonListResponse = pokemonListResponse.results.map { pokemon ->
                val id = extractIdUrl(pokemon.url)
                PokemonListModel(
                    id.toInt(),
                    pokemon.name,
                    pokemon.url,
                    LITTLE_IMAGE.replace("[id]", id),
                    BIG_IMAGE.replace("[id]", id),
                    "",
                    ""
                )
            }
            return PokemonListState.Success(apiPokemonListResponse)

        }.onFailure {
            return PokemonListState.Error
        }
        return PokemonListState.Success(emptyList())
    }

    private suspend fun getAPITypeList(): List<TypeModel> {
        val pokemonTypes = getAPITypes()
        val typeResponse = mutableListOf<TypeModel>()
        pokemonTypes.forEach { type ->
            runCatching { pokemonApiService.getType(type) }.onSuccess { response ->
                response.pokemon.forEach { pokemon ->
                    typeResponse.add(
                        TypeModel(id = response.id,
                            name = response.name,
                            typeSlot = pokemon.slot,
                            namePokemon = pokemon.pokemon.name,
                            doubleDamageFrom = response.damageRelations.doubleDamageFrom.map { it.name },
                            doubleDamageTo = response.damageRelations.doubleDamageTo.map { it.name },
                            halfDamageFrom = response.damageRelations.halfDamageFrom.map { it.name },
                            halfDamageTo = response.damageRelations.halfDamageTo.map { it.name },
                            noDamageFrom = response.damageRelations.noDamageFrom.map { it.name },
                            noDamageTo = response.damageRelations.noDamageTo.map { it.name })
                    )
                }
            }.onFailure {
                return emptyList()
            }
        }
        return typeResponse
    }

    private suspend fun getAPITypes(): List<String> {
        runCatching {
            pokemonApiService.getTypeList(POKEMON_LIMIT, POKEMON_OFFSET)
        }.onSuccess { pokemonListResponse ->
            return pokemonListResponse.results.map { it.name }
        }.onFailure {
            return emptyList()
        }
        return emptyList()
    }

    override suspend fun getPokemonInfo(namePokemon: String): PokemonInfoState {
        val apiPokemonModel = getAPIPokemon(namePokemon)
        val apiTypeModel = getAPIType(namePokemon)
        val apiAbilitiesModel = getAPIAbility(apiPokemonModel?.abilities ?: emptyList())
        val apiSpeciesModel = getAPISpecies(namePokemon)
        val apiEvolutionModel = getAPIEvolution(apiSpeciesModel?.idEvolutionChain ?: "")

        if (apiPokemonModel != null && apiTypeModel != null && apiAbilitiesModel != null && apiSpeciesModel != null && apiEvolutionModel != null) {
            val pokemonModel = PokemonModel(
                apiPokemonModel,
                apiTypeModel,
                apiAbilitiesModel,
                apiSpeciesModel,
                apiEvolutionModel
            )
            return PokemonInfoState.Success(pokemonModel)
        }
        return PokemonInfoState.Error
    }

    private suspend fun getAPIPokemon(namePokemon: String): PokemonInfoModel? {
        val roomInfoResponse = pokemonDao.getPokemonInfo(namePokemon)
        if (roomInfoResponse.id != 0) {
            return roomInfoResponse.toDomain()
        }
        runCatching {
            pokemonApiService.getPokemon(namePokemon)
        }.onSuccess { pokemonInfoResponse ->
            val apiPokemonInfoResponse = PokemonInfoModel(
                id = pokemonInfoResponse.id,
                name = pokemonInfoResponse.name,
                abilities = pokemonInfoResponse.abilities.map { it.ability.name },
                legacyCries = pokemonInfoResponse.cries.legacy,
                latestCries = pokemonInfoResponse.cries.latest,
                baseExperience = pokemonInfoResponse.baseExperience,
                hpStat = pokemonInfoResponse.stats[0].baseStat,
                attackStat = pokemonInfoResponse.stats[1].baseStat,
                defenseStat = pokemonInfoResponse.stats[2].baseStat,
                specialAttackStat = pokemonInfoResponse.stats[3].baseStat,
                specialDefenseStat = pokemonInfoResponse.stats[4].baseStat,
                speedStat = pokemonInfoResponse.stats[5].baseStat,
                imageLittle = LITTLE_IMAGE.replace("[id]", pokemonInfoResponse.id.toString()),
                imageBig = BIG_IMAGE.replace("[id]", pokemonInfoResponse.id.toString()),
                image3D = DDD_IMAGE.replace("[id]", pokemonInfoResponse.id.toString()),
                height = pokemonInfoResponse.height,
                weight = pokemonInfoResponse.weight
            )

            //Clear and save again the values.
            pokemonDao.clearPokemonInfo()
            pokemonDao.insertPokemonInfo(apiPokemonInfoResponse.toRoom())
            return apiPokemonInfoResponse
        }.onFailure {
            return null
        }
        return null
    }

    private suspend fun getAPIType(namePokemon: String): List<TypeModel>? {
        val roomTypeResponse = pokemonDao.getType(namePokemon)
        if (roomTypeResponse.isNotEmpty()) {
            return roomTypeResponse.map { it.toDomain() }
        }
        return null
    }

    private suspend fun getAPIAbility(abilities: List<String>): List<AbilityModel>? {
        val roomAbilityResponse = mutableListOf<AbilityEntity>()
        abilities.forEach { nameAbility ->
            roomAbilityResponse.add(pokemonDao.getAbility(nameAbility))
        }
        if (roomAbilityResponse.isNotEmpty()) {
            return roomAbilityResponse.map { it.toDomain() }
        }
        var nameAbility = ""
        var descriptionAbility = ""
        val apiAbilityResponse = mutableListOf<AbilityModel>()
        apiAbilityResponse.forEach { ability ->
            runCatching {
                pokemonApiService.getAbility(ability.name)
            }.onSuccess { response ->
                response.flavorTextEntries.forEach {
                    if (it.language.name == "es") {
                        nameAbility = it.flavorText
                    }
                }
                response.names.forEach {
                    if (it.language.name == "es") {
                        descriptionAbility = it.name
                    }
                }
                apiAbilityResponse.add(
                    AbilityModel(
                        id = response.id,
                        name = nameAbility,
                        description = descriptionAbility
                    )
                )
            }.onFailure {
                return null
            }
        }
        //Clear and save again the values.
        pokemonDao.clearAbility()
        pokemonDao.insertAbility(apiAbilityResponse.map { it.toRoom() })
        return apiAbilityResponse
    }

    private suspend fun getAPISpecies(namePokemon: String): SpeciesModel? {
        val roomSpeciesResponse = pokemonDao.getSpecies(namePokemon)
        if (roomSpeciesResponse.id != 0) {
            return roomSpeciesResponse.toDomain()
        }
        runCatching {
            pokemonApiService.getSpecies(namePokemon)
        }.onSuccess { pokemonSpeciesResponse ->
            val idEvolutionChain = extractIdUrl(pokemonSpeciesResponse.evolutionChain.url)
            var descriptionSpecies = ""
            var pokemonClass = ""
            pokemonSpeciesResponse.flavorTextEntries.forEach {
                if (it.language.name == "es") {
                    descriptionSpecies = it.flavorText
                }
            }
            pokemonSpeciesResponse.genera.forEach {
                if (it.language.name == "es") {
                    pokemonClass = it.genus
                }
            }
            val apiPokemonSpeciesResponse = SpeciesModel(
                id = pokemonSpeciesResponse.id,
                name = pokemonSpeciesResponse.name,
                baseHappiness = pokemonSpeciesResponse.baseHappiness,
                captureRate = pokemonSpeciesResponse.captureRate,
                idEvolutionChain = idEvolutionChain,
                description = descriptionSpecies,
                pokemonClass = pokemonClass,
                habitat = pokemonSpeciesResponse.habitat.name,
                isBaby = pokemonSpeciesResponse.isBaby,
                isLegendary = pokemonSpeciesResponse.isLegendary,
                isMythical = pokemonSpeciesResponse.isMythical
            )

            //Clear and save again the values.
            pokemonDao.clearSpecies()
            pokemonDao.insertSpecies(apiPokemonSpeciesResponse.toRoom())
            return apiPokemonSpeciesResponse
        }.onFailure {
            return null
        }
        return null
    }

    private suspend fun getAPIEvolution(idEvolutionChain: String): List<EvolutionModel>? {
        val roomEvolutionResponse = pokemonDao.getEvolution(idEvolutionChain)
        if (roomEvolutionResponse.isNotEmpty()) {
            return roomEvolutionResponse.map { it.toDomain() }
        }
        runCatching {
            pokemonApiService.getEvolution(idEvolutionChain)
        }.onSuccess { pokemonEvolutionResponse ->
            val apiEvolutionResponse = mutableListOf<EvolutionModel>()
            pokemonEvolutionResponse.chain.evolvesTo.forEach { evolvesToFirst ->
                apiEvolutionResponse.add(
                    EvolutionModel(
                        id = 0,
                        idEvolution = idEvolutionChain,
                        namePokemon = pokemonEvolutionResponse.chain.species.name,
                        idPokemonEvolution = extractIdUrl(evolvesToFirst.species.url),
                        nameEvolution = evolvesToFirst.species.name,
                        itemEvolution = evolvesToFirst.evolutionDetails[0].item.name,
                        minLevel = evolvesToFirst.evolutionDetails[0].minLevel,
                        trigger = evolvesToFirst.evolutionDetails[0].trigger.name,
                        happiness = evolvesToFirst.evolutionDetails[0].minHappiness,
                        timeOfDay = evolvesToFirst.evolutionDetails[0].timeOfDay,
                        location = evolvesToFirst.evolutionDetails[0].location.name
                    )
                )
                evolvesToFirst.evolvesTo.forEach { evolvesToSecond ->
                    apiEvolutionResponse.add(
                        EvolutionModel(
                            id = 0,
                            idEvolution = idEvolutionChain,
                            namePokemon = evolvesToFirst.species.name,
                            idPokemonEvolution = extractIdUrl(evolvesToSecond.species.url),
                            nameEvolution = evolvesToSecond.species.name,
                            itemEvolution = evolvesToSecond.evolutionDetails[0].item.name,
                            minLevel = evolvesToSecond.evolutionDetails[0].minLevel,
                            trigger = evolvesToSecond.evolutionDetails[0].trigger.name,
                            happiness = evolvesToSecond.evolutionDetails[0].minHappiness,
                            timeOfDay = evolvesToSecond.evolutionDetails[0].timeOfDay,
                            location = evolvesToSecond.evolutionDetails[0].location.name
                        )
                    )
                }
            }

            //Clear and save again the values.
            pokemonDao.clearEvolution()
            pokemonDao.insertEvolution(apiEvolutionResponse.map { it.toRoom() })
            return apiEvolutionResponse
        }.onFailure {
            return null
        }
        return null
    }

    private fun extractIdUrl(url: String): String {
        return if (url.endsWith("/")) {
            url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            url.takeLastWhile { it.isDigit() }
        }
    }
}