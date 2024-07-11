package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokedex.core.extensions.formatHeightPokemon
import com.example.pokedex.core.extensions.formatWeightPokemon
import com.example.pokedex.databinding.FragmentAboutBinding
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AboutFragment : Fragment() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        initStateUI()
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentsManager.pokemonInfo.collect { pokemonInfo ->
                    changeUI(pokemonInfo)
                }
            }
        }
    }

    private fun changeUI(pokemonInfo: PokemonModel?) {
        if (pokemonInfo != null) {
            if (pokemonInfo.speciesModel != null) {
                binding.layoutDescription.text = pokemonInfo.speciesModel.description
                binding.cvBaby.isVisible = pokemonInfo.speciesModel.isBaby
                binding.cvLegendary.isVisible = pokemonInfo.speciesModel.isLegendary
                binding.cvMythic.isVisible = pokemonInfo.speciesModel.isMythical
                binding.layoutType.isVisible =
                    pokemonInfo.speciesModel.isBaby or pokemonInfo.speciesModel.isLegendary or pokemonInfo.speciesModel.isMythical

                binding.tvHabitat.text = pokemonInfo.speciesModel.habitat
                binding.tvHappiness.text = pokemonInfo.speciesModel.baseHappiness.toString()
                binding.tvCaptureRate.text =
                    pokemonInfo.speciesModel.captureRate.toString()
            }
            if (pokemonInfo.pokemonInfoModel != null) {
                binding.tvWeight.text =
                    pokemonInfo.pokemonInfoModel.weight.toString().formatWeightPokemon()
                binding.tvHeight.text =
                    pokemonInfo.pokemonInfoModel.height.toString().formatHeightPokemon()
            }
            if (pokemonInfo.abilityModel != null) {
                val abilities = emptyList<String>().toMutableList()
                pokemonInfo.abilityModel.forEach {
                    abilities += it.name
                }
                binding.layoutAbilites.isVisible = abilities.isNotEmpty()
                binding.tvAbilities.text = abilities.joinToString(separator = ", ")
            }
        }
    }
}