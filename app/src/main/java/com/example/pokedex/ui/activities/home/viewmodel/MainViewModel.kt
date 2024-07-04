package com.example.pokedex.ui.activities.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.DispatcherProvider
import com.example.pokedex.di.Constants.ONE_COLUMN_LIST
import com.example.pokedex.domain.state.PokemonListState
import com.example.pokedex.domain.usecase.GetPokemonListUseCase
import com.example.pokedex.domain.usecase.ReadColumnsListUseCase
import com.example.pokedex.domain.usecase.ReadDarkModeUseCase
import com.example.pokedex.domain.usecase.ReadPokemonTypeUseCase
import com.example.pokedex.domain.usecase.SaveColumnsListUseCase
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
    private val savePokemonTypeUseCase: SavePokemonTypeUseCase,
    private val readColumnsListUseCase: ReadColumnsListUseCase,
    private val saveColumnsListUseCase: SaveColumnsListUseCase
) : ViewModel() {

    private val _pokemonListState = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val pokemonListState: StateFlow<PokemonListState> = _pokemonListState

    private val _columnsList = MutableStateFlow(ONE_COLUMN_LIST)
    val columnsList: StateFlow<Int> = _columnsList

    private val _darkMode = MutableStateFlow<Boolean?>(null)
    val darkMode: StateFlow<Boolean?> = _darkMode

    private val _pokemonType = MutableStateFlow<String?>(null)
    val pokemonType: StateFlow<String?> = _pokemonType

    init {
        readColumnsList()
        readDarkMode()
        readPokemonType()
        getPokemonList()
    }

    private fun readColumnsList() {
        viewModelScope.launch {
            _columnsList.value = withContext(dispatcherProvider.io) {
                readColumnsListUseCase.invoke()
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

    private fun readPokemonType() {
        viewModelScope.launch {
            _pokemonType.value = withContext(dispatcherProvider.io) {
                readPokemonTypeUseCase.invoke()
            }
        }
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _pokemonListState.value = PokemonListState.Loading
            _pokemonListState.value = withContext(dispatcherProvider.io) {
                getPokemonListUseCase.invoke()
            }
        }
    }

    fun saveColumnsList(columnsList: Int) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                saveColumnsListUseCase.invoke(columnsList)
                _columnsList.value = columnsList
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

    fun savePokemonType(pokemonType: String) {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                savePokemonTypeUseCase.invoke(pokemonType)
                _pokemonType.value = pokemonType
            }
        }
    }
}