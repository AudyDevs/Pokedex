package com.example.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokedex.databinding.FragmentStatsBinding
import com.example.pokedex.domain.model.PokemonModel
import com.example.pokedex.ui.fragments.manager.FragmentsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StatsFragment : Fragment() {

    @Inject
    lateinit var fragmentsManager: FragmentsManager

    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(layoutInflater, container, false)
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
            if (pokemonInfo.pokemonInfoModel != null) {
                binding.tvHpStat.text = pokemonInfo.pokemonInfoModel.hpStat.toString()
                binding.tvAttackStat.text = pokemonInfo.pokemonInfoModel.attackStat.toString()
                binding.tvDefenseStat.text = pokemonInfo.pokemonInfoModel.defenseStat.toString()
                binding.tvSpecialAttackStat.text =
                    pokemonInfo.pokemonInfoModel.specialAttackStat.toString()
                binding.tvSpecialDefenseStat.text =
                    pokemonInfo.pokemonInfoModel.specialDefenseStat.toString()
                binding.tvSpeedStat.text = pokemonInfo.pokemonInfoModel.speedStat.toString()
                binding.tvBaseExperience.text =
                    pokemonInfo.pokemonInfoModel.baseExperience.toString()

                binding.progressHpStat.max = 255
                binding.progressAttackStat.max = 255
                binding.progressDefenseStat.max = 255
                binding.progressSpecialAttackStat.max = 255
                binding.progressSpecialDefenseStat.max = 255
                binding.progressSpeedStat.max = 255

                binding.progressHpStat.progress = pokemonInfo.pokemonInfoModel.hpStat
                binding.progressAttackStat.progress = pokemonInfo.pokemonInfoModel.attackStat
                binding.progressDefenseStat.progress = pokemonInfo.pokemonInfoModel.defenseStat
                binding.progressSpecialAttackStat.progress =
                    pokemonInfo.pokemonInfoModel.specialAttackStat
                binding.progressSpecialDefenseStat.progress =
                    pokemonInfo.pokemonInfoModel.specialDefenseStat
                binding.progressSpeedStat.progress = pokemonInfo.pokemonInfoModel.speedStat
            }
        }
    }
}