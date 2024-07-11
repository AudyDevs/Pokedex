package com.example.pokedex.ui.activities.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.DispatcherProvider
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.domain.state.PokemonInfoState
import com.example.pokedex.domain.usecase.GetPokemonByIDUseCase
import com.example.pokedex.domain.usecase.GetPokemonInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getPokemonInfoUseCase: GetPokemonInfoUseCase,
    private val getPokemonByIDUseCase: GetPokemonByIDUseCase
) : ViewModel() {

    private val _pokemonInfoState = MutableStateFlow<PokemonInfoState>(PokemonInfoState.Loading)
    val pokemonInfoState: StateFlow<PokemonInfoState> = _pokemonInfoState

    var pokemonID: Int = 0
    var namePokemon: String = ""

    fun getPokemonInfo() {
        viewModelScope.launch {
            _pokemonInfoState.value = PokemonInfoState.Loading
            _pokemonInfoState.value = withContext(dispatcherProvider.io) {
                getPokemonInfoUseCase.invoke(namePokemon)
            }
        }
    }

    fun getPokemonInfoByID() {
        viewModelScope.launch {
            namePokemon = withContext(dispatcherProvider.io) {
                getPokemonByIDUseCase.invoke(pokemonID)
            }
            getPokemonInfo()
        }
    }
}