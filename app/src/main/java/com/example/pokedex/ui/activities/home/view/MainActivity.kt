package com.example.pokedex.ui.activities.home.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.domain.state.PokemonListState
import com.example.pokedex.ui.activities.home.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
    }

    private fun initUI() {
        initListeners()
        initAdapter()
        initStateUI()
    }

    private fun initListeners() {
        binding.btnPokemonList.setOnClickListener {
            mainViewModel.savePokemonType("water")
        }
        binding.darkMode.setOnCheckedChangeListener { _, isSelected ->
            if (isSelected) {
                mainViewModel.saveDarkMode(true)
            } else {
                mainViewModel.saveDarkMode(false)
            }
        }
    }

    private fun initAdapter() {
        //Recyclerview
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.pokemonListState.collect { pokemonListState ->
                    when (pokemonListState) {
                        PokemonListState.Loading -> onLoading()
                        is PokemonListState.Success -> onSuccess()
                        PokemonListState.Error -> onError()
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.darkMode.collect { darkMode ->
                    if (darkMode != null) {
                        if (darkMode) {
                            enableDarkMode()
                        } else {
                            disableDarkMode()
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.pokemonType.collect { pokemonType ->
                    val a = pokemonType
                    //update recycler view with filter.
                    // null es tots els tipus.
                }
            }
        }
    }

    private fun onLoading() {
        //Mostra Timer
    }

    private fun onSuccess() {
        //Update recyclerview
    }

    private fun onError() {
        //Mostra Dialog
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}