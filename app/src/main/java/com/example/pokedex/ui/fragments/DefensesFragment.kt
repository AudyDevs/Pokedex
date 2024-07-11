package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokedex.databinding.FragmentDefensesBinding
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DefensesFragment : Fragment() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private var _binding: FragmentDefensesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefensesBinding.inflate(layoutInflater, container, false)
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
            binding.text.text = pokemonInfo.pokemonInfoModel?.name
        }
    }
}