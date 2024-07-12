package com.example.pokedex.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.FragmentDefenseBinding
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.fragments.adapter.DefenseAdapter
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DefenseFragment : Fragment() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private lateinit var defenseX4Adapter: DefenseAdapter
    private lateinit var defenseX2Adapter: DefenseAdapter
    private lateinit var defenseX1Adapter: DefenseAdapter
    private lateinit var defenseX05Adapter: DefenseAdapter
    private lateinit var defenseX025Adapter: DefenseAdapter
    private lateinit var defenseX0Adapter: DefenseAdapter
    private var _binding: FragmentDefenseBinding? = null
    private val binding get() = _binding!!

    private val allPokemonTypes = listOf(
        "bug",
        "dark",
        "dragon",
        "electric",
        "fairy",
        "fighting",
        "fire",
        "flying",
        "ghost",
        "grass",
        "ground",
        "ice",
        "normal",
        "poison",
        "psychic",
        "rock",
        "steel",
        "water"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDefenseBinding.inflate(layoutInflater, container, false)
        initUI()
        return binding.root
    }
    private fun initUI() {
        initAdapter()
        initStateUI()
    }

    private fun initAdapter() {
        defenseX4Adapter = DefenseAdapter()
        defenseX2Adapter = DefenseAdapter()
        defenseX1Adapter = DefenseAdapter()
        defenseX05Adapter = DefenseAdapter()
        defenseX025Adapter = DefenseAdapter()
        defenseX0Adapter = DefenseAdapter()
        binding.rvDamageX4.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX4Adapter
        }
        binding.rvDamageX2.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX2Adapter
        }
        binding.rvDamageX1.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX1Adapter
        }
        binding.rvDamageX05.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX05Adapter
        }
        binding.rvDamageX025.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX025Adapter
        }
        binding.rvDamageX0.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = defenseX0Adapter
        }
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
        val damageX4: MutableList<String> = mutableListOf()
        val damageX2: MutableList<String> = mutableListOf()
        val damageX1: MutableList<String> = mutableListOf()
        val damageX05: MutableList<String> = mutableListOf()
        val damageX025: MutableList<String> = mutableListOf()
        val damageX0: MutableList<String> = mutableListOf()

        if (pokemonInfo != null) {
            if (pokemonInfo.typeModel != null) {
                if (pokemonInfo.typeModel.size == 1) {
                    damageX2.addAll(pokemonInfo.typeModel.first().doubleDamageFrom)
                    damageX05.addAll(pokemonInfo.typeModel.first().halfDamageFrom)
                    damageX0.addAll(pokemonInfo.typeModel.first().noDamageFrom)
                } else {
                    allPokemonTypes.forEach { type ->
                        var powerType = 1f
                        if (pokemonInfo.typeModel[0].doubleDamageFrom.contains(type)) {
                            powerType *= 2
                        }
                        if (pokemonInfo.typeModel[1].doubleDamageFrom.contains(type)) {
                            powerType *= 2
                        }
                        if (pokemonInfo.typeModel[0].halfDamageFrom.contains(type)) {
                            powerType *= 0.5f
                        }
                        if (pokemonInfo.typeModel[1].halfDamageFrom.contains(type)) {
                            powerType *= 0.5f
                        }
                        if (pokemonInfo.typeModel[0].noDamageFrom.contains(type)) {
                            powerType *= 0f
                        }
                        if (pokemonInfo.typeModel[1].noDamageFrom.contains(type)) {
                            powerType *= 0f
                        }
                        when (powerType) {
                            4f -> {
                                damageX4.add(type)
                            }

                            2f -> {
                                damageX2.add(type)
                            }

                            1f -> {
                                damageX1.add(type)
                            }

                            0.5f -> {
                                damageX05.add(type)
                            }

                            0.25f -> {
                                damageX025.add(type)
                            }

                            0f -> {
                                damageX0.add(type)
                            }
                        }
                    }
                }
            }
            if (damageX4.isEmpty()) {
                binding.rvDamageX4.isVisible = false
                binding.layoutDamageX4.isVisible = false
            } else {
                binding.rvDamageX4.isVisible = true
                binding.layoutDamageX4.isVisible = true
                defenseX4Adapter.updateList(damageX4)
            }
            if (damageX2.isEmpty()) {
                binding.rvDamageX2.isVisible = false
                binding.layoutDamageX2.isVisible = false
            } else {
                binding.rvDamageX2.isVisible = true
                binding.layoutDamageX2.isVisible = true
                defenseX2Adapter.updateList(damageX2)
            }
            if (damageX1.isEmpty()) {
                binding.rvDamageX1.isVisible = false
                binding.layoutDamageX1.isVisible = false
            } else {
                binding.rvDamageX1.isVisible = true
                binding.layoutDamageX1.isVisible = true
                defenseX1Adapter.updateList(damageX1)
            }
            if (damageX05.isEmpty()) {
                binding.rvDamageX05.isVisible = false
                binding.layoutDamageX05.isVisible = false
            } else {
                binding.rvDamageX05.isVisible = true
                binding.layoutDamageX05.isVisible = true
                defenseX05Adapter.updateList(damageX05)
            }
            if (damageX025.isEmpty()) {
                binding.rvDamageX025.isVisible = false
                binding.layoutDamageX025.isVisible = false
            } else {
                binding.rvDamageX025.isVisible = true
                binding.layoutDamageX025.isVisible = true
                defenseX025Adapter.updateList(damageX025)
            }
            if (damageX0.isEmpty()) {
                binding.rvDamageX0.isVisible = false
                binding.layoutDamageX0.isVisible = false
            } else {
                binding.rvDamageX0.isVisible = true
                binding.layoutDamageX0.isVisible = true
                defenseX0Adapter.updateList(damageX0)
            }
        }
    }
}