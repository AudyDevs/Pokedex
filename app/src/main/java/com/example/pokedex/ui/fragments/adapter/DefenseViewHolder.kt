package com.example.pokedex.ui.fragments.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.core.extensions.backgroundColorType
import com.example.pokedex.core.extensions.iconPokemonType
import com.example.pokedex.core.extensions.textPokemonType
import com.example.pokedex.databinding.ItemDefenseBinding

class DefenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDefenseBinding.bind(view)

    fun render(defense: String) {
        binding.apply {
            ivType1.setCardBackgroundColor(
                ContextCompat.getColor(
                    binding.ivType1.context, defense.backgroundColorType()
                )
            )
            binding.ivIconType1.setImageResource(defense.iconPokemonType())
            binding.ivTextType1.text = defense.textPokemonType()
        }
    }
}