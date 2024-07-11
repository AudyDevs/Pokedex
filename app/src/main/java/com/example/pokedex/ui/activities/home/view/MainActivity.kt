package com.example.pokedex.ui.activities.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.pokedex.R
import com.example.pokedex.core.extensions.iconPokemonType
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.di.Constants.ONE_COLUMN_LIST
import com.example.pokedex.di.Constants.TWO_COLUMN_LIST
import com.example.pokedex.domain.model.PokemonListModel
import com.example.pokedex.domain.state.PokemonListState
import com.example.pokedex.ui.activities.detail.view.DetailActivity
import com.example.pokedex.ui.activities.home.adapter.MainAdapter
import com.example.pokedex.ui.activities.home.viewmodel.MainViewModel
import com.example.pokedex.ui.dialogs.DialogError
import com.example.pokedex.ui.dialogs.DialogTypes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter
    private var currentPokemonList: List<PokemonListModel> = emptyList()

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
        binding.ivPokemonType.setOnClickListener {
            DialogTypes(this, onClickButtonType = { type ->
                mainViewModel.savePokemonType(type)
            })
        }
        binding.ivNumberColumns.setOnClickListener {
            if (mainViewModel.columnsList.value == ONE_COLUMN_LIST) {
                mainViewModel.saveColumnsList(TWO_COLUMN_LIST)
                binding.ivNumberColumns.setImageResource(R.drawable.ic_two_columns)
            } else if (mainViewModel.columnsList.value == TWO_COLUMN_LIST) {
                mainViewModel.saveColumnsList(ONE_COLUMN_LIST)
                binding.ivNumberColumns.setImageResource(R.drawable.ic_one_column)
            }

            mainAdapter.updateViewType(mainViewModel.columnsList.value)
            binding.rvPokemon.layoutManager =
                GridLayoutManager(
                    binding.rvPokemon.context,
                    mainViewModel.columnsList.value
                )
            binding.rvPokemon.adapter = mainAdapter
        }
        binding.ivDarkMode.setOnClickListener {
            if (binding.ivDarkMode.tag == "ic_solRock") {
                mainViewModel.saveDarkMode(true)
                binding.ivDarkMode.setImageResource(R.drawable.ic_lunatone)
                binding.ivDarkMode.tag = "ic_lunaTone"
            } else {
                mainViewModel.saveDarkMode(false)
                binding.ivDarkMode.setImageResource(R.drawable.ic_solrock)
                binding.ivDarkMode.tag = "ic_solRock"
            }
        }
    }

    private fun initAdapter() {
        mainAdapter = MainAdapter(onItemSelected = { pokemonListModel ->
            navigateToDetailActivity(pokemonListModel.id, pokemonListModel.name)
        })
        binding.rvPokemon.apply {
            layoutManager = GridLayoutManager(context, ONE_COLUMN_LIST)
            adapter = mainAdapter
        }
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.pokemonListState.collect { pokemonListState ->
                    when (pokemonListState) {
                        PokemonListState.Loading -> onLoading()
                        is PokemonListState.Success -> onSuccess(pokemonListState.pokemon)
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
                            disableDarkMode()
                            binding.ivDarkMode.setImageResource(R.drawable.ic_lunatone)
                            binding.ivDarkMode.tag = "ic_lunaTone"
                        } else {
                            enableDarkMode()
                            binding.ivDarkMode.setImageResource(R.drawable.ic_solrock)
                            binding.ivDarkMode.tag = "ic_solRock"
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.columnsList.collect { columnsList ->
                    if (columnsList == ONE_COLUMN_LIST) {
                        binding.ivNumberColumns.setImageResource(R.drawable.ic_one_column)
                    } else if (columnsList == TWO_COLUMN_LIST) {
                        binding.ivNumberColumns.setImageResource(R.drawable.ic_two_columns)
                    }
                    mainAdapter.updateViewType(columnsList)
                    binding.rvPokemon.layoutManager =
                        GridLayoutManager(binding.rvPokemon.context, columnsList)
                    binding.rvPokemon.adapter = mainAdapter
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.pokemonType.collect { type ->
                    if (type.isNullOrEmpty()) {
                        binding.ivPokemonType.setImageResource(R.drawable.ic_pokeball_grey)
                    } else {
                        binding.ivPokemonType.setImageResource(type.iconPokemonType())
                    }
                    updateFilteredList()
                }
            }
        }
    }

    private fun onLoading() {
        startAnimation()
    }

    private fun onSuccess(pokemonList: List<PokemonListModel>) {
        stopAnimation()
        currentPokemonList = pokemonList
        updateFilteredList()
    }

    private fun updateFilteredList() {
        if (mainViewModel.pokemonType.value.isNullOrEmpty()) {
            mainAdapter.updateList(currentPokemonList)
        } else {
            val filteredList =
                currentPokemonList.filter { it.typeSlot1 == mainViewModel.pokemonType.value || it.typeSlot2 == mainViewModel.pokemonType.value }
            mainAdapter.updateList(filteredList)
        }
    }

    private fun onError() {
        stopAnimation()
        DialogError(this, onClickButtonError = {})
    }

    private fun startAnimation() {
        binding.ivLottieAnimation.isVisible = true
        binding.ivLottieAnimation.setAnimation(R.raw.loading_pokeball)
        binding.ivLottieAnimation.repeatCount = LottieDrawable.INFINITE
        binding.ivLottieAnimation.playAnimation()
    }

    private fun stopAnimation() {
        binding.ivLottieAnimation.isVisible = false
        binding.ivLottieAnimation.repeatCount = 0
        binding.ivLottieAnimation.pauseAnimation()
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    private fun navigateToDetailActivity(idPokemon: Int, namePokemon: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("idPokemon", idPokemon)
        intent.putExtra("namePokemon", namePokemon)
        startActivity(intent)
    }
}