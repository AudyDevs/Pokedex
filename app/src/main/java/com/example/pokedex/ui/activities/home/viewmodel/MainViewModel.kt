package com.example.pokedex.ui.activities.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.DispatcherProvider
import com.example.pokedex.domain.state.PokemonListState
import com.example.pokedex.domain.usecase.GetPokemonListUseCase
import com.example.pokedex.domain.usecase.ReadDarkModeUseCase
import com.example.pokedex.domain.usecase.ReadPokemonTypeUseCase
import com.example.pokedex.domain.usecase.SaveDarkModeUseCase
import com.example.pokedex.domain.usecase.SavePokemonTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val readDarkModeUseCase: ReadDarkModeUseCase,
    private val saveDarkModeUseCase: SaveDarkModeUseCase,
    private val readPokemonTypeUseCase: ReadPokemonTypeUseCase,
    private val savePokemonTypeUseCase: SavePokemonTypeUseCase
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState

    private val _darkMode = MutableStateFlow<Boolean?>(null)
    val darkMode: StateFlow<Boolean?> = _darkMode

    private val _pokemonType = MutableStateFlow<String?>(null)
    val pokemonType: StateFlow<String?> = _pokemonType

    init {
        readDarkMode()
        readPokemonType()
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = PokemonListState.Loading
            _pokemonListState.value = withContext(dispatcherProvider.io) {
                getPokemonListUseCase.invoke()
            }
        }
    }

    private fun readDarkMode() {
        viewModelScope.launch {
            _darkMode.value = withContext(dispatcherProvider.io) {
                readDarkModeUseCase.invoke()
            }
        }
    }

    fun saveDarkMode(darkMode: Boolean) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                saveDarkModeUseCase.invoke(darkMode)
                _darkMode.value = darkMode
            }
        }
    }

    private fun readPokemonType() {
        viewModelScope.launch {
            _pokemonType.value = withContext(dispatcherProvider.io) {
                readPokemonTypeUseCase.invoke()
            }
        }
    }

    fun savePokemonType(pokemonType: String) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                savePokemonTypeUseCase.invoke(pokemonType)
                _pokemonType.value = pokemonType
            }
        }
    }
}