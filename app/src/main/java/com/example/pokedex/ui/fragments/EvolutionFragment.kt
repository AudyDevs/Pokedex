package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentEvolutionBinding
import com.example.pokedex.ui.fragments.adapter.EvolutionAdapter
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class EvolutionFragment : Fragment() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private lateinit var evolutionAdapter: EvolutionAdapter
    private var _binding: FragmentEvolutionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvolutionBinding.inflate(layoutInflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        initAdapter()
        initStateUI()
    }

    private fun initAdapter() {
        evolutionAdapter = EvolutionAdapter()
        binding.rvEvolution.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = evolutionAdapter
        }
    }

    private fun initStateUI() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentsManager.pokemonInfo.collect { pokemonInfo ->
                    if (pokemonInfo != null) {
                        if (pokemonInfo.evolutionModel != null) {
                            evolutionAdapter.updateList(pokemonInfo.evolutionModel)
                        }
                    }
                }
            }
        }
    }
}