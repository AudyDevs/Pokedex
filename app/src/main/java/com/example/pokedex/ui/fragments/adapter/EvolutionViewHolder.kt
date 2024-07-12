package com.example.pokedex.ui.fragments.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ItemEvolutionBinding
import com.example.pokedex.di.Constants
import com.example.pokedex.domain.model.EvolutionModel

class EvolutionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemEvolutionBinding.bind(view)

    fun render(evolutionModel: EvolutionModel) {
        binding.apply {
            val imagePokemonOrigen =
                Constants.BIG_IMAGE.replace("[id]", evolutionModel.idPokemonOrigin)
            val imagePokemonEvolution =
                Constants.BIG_IMAGE.replace("[id]", evolutionModel.idPokemonEvolution)

            Glide.with(ivPokemonOrigin.context)
                .load(imagePokemonOrigen)
                .into(ivPokemonOrigin)

            Glide.with(ivPokemonEvolution.context)
                .load(imagePokemonEvolution)
                .into(ivPokemonEvolution)

            binding.tvNamePokemonOrigin.text = evolutionModel.namePokemon
            binding.tvNamePokemonEvolution.text = evolutionModel.nameEvolution

            if (evolutionModel.minLevel.toString().isNotEmpty()) {
                when (evolutionModel.trigger) {
                    "trade" -> {
                        binding.layoutLevel.isVisible = true
                        binding.tvTypeLevel.text = "Cable Link"
                        binding.tvLevel.text = ""
                    }

                    else -> {
                        if (evolutionModel.minLevel > 0) {
                            binding.layoutLevel.isVisible = true
                            binding.tvLevel.text = evolutionModel.minLevel.toString()
                        } else {
                            binding.layoutLevel.isVisible = false
                        }
                    }
                }
            } else {
                binding.layoutLevel.isVisible = false
            }
            if (evolutionModel.itemEvolution.isNotEmpty()) {
                binding.layoutItem.isVisible = true
                binding.tvItem.text = evolutionModel.itemEvolution
            } else {
                binding.layoutItem.isVisible = false
            }
            if (evolutionModel.happiness.toString().isNotEmpty()) {
                if (evolutionModel.happiness > 0) {
                    binding.layoutHappiness.isVisible = true
                    binding.tvHappiness.text = evolutionModel.happiness.toString()
                } else {
                    binding.layoutHappiness.isVisible = false
                }
            } else {
                binding.layoutHappiness.isVisible = false
            }
            if (evolutionModel.timeOfDay.isNotEmpty()) {
                binding.layoutTime.isVisible = true
                binding.tvTime.text = evolutionModel.timeOfDay
            } else {
                binding.layoutTime.isVisible = false
            }
            if (evolutionModel.location.isNotEmpty()) {
                binding.layoutLocation.isVisible = true
                binding.tvLocation.text = evolutionModel.location
            } else {
                binding.layoutLocation.isVisible = false
            }
        }
    }
}