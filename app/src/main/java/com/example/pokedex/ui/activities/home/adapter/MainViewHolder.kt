package com.example.pokedex.ui.activities.home.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.core.extensions.backgroundColorType
import com.example.pokedex.core.extensions.iconPokemonType
import com.example.pokedex.databinding.ItemPokemonOneColumnBinding
import com.example.pokedex.databinding.ItemPokemonTwoColumnsBinding
import com.example.pokedex.di.Constants.ONE_COLUMN_LIST
import com.example.pokedex.domain.model.PokemonListModel
import java.util.Locale

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val bindingOne = ItemPokemonOneColumnBinding.bind(view)
    private val bindingTwo = ItemPokemonTwoColumnsBinding.bind(view)

    fun render(
        pokemon: PokemonListModel,
        viewType: Int,
        onItemSelected: (PokemonListModel) -> Unit
    ) {
        if (viewType == ONE_COLUMN_LIST) {
            bindingOne.apply {
                layoutCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                        layoutCard.context,
                        pokemon.typeSlot1.backgroundColorType()
                    )
                )
                tvId.text = pokemon.id.toString()
                Glide.with(ivIcon.context)
                    .load(pokemon.imageLittle)
                    .into(ivIcon)
                tvIdText.text = String.format("#%04d", pokemon.id)
                tvName.text = pokemon.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                if (pokemon.typeSlot1.isNotEmpty()) {
                    ivType1.setImageResource(pokemon.typeSlot1.iconPokemonType())
                }
                if (pokemon.typeSlot2.isNotEmpty()) {
                    ivType2.setImageResource(pokemon.typeSlot2.iconPokemonType())
                    ivType2.isVisible = true
                } else {
                    ivType2.isVisible = false
                }
                layoutCard.setOnClickListener { onItemSelected(pokemon) }
            }
        } else {
            bindingTwo.apply {
                layoutCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                        layoutCard.context,
                        pokemon.typeSlot1.backgroundColorType()
                    )
                )
                tvId.text = pokemon.id.toString()
                Glide.with(ivIcon.context)
                    .load(pokemon.imageBig)
                    .into(ivIcon)
                tvIdText.text = String.format("#%04d", pokemon.id)
                tvName.text = pokemon.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                if (pokemon.typeSlot1.isNotEmpty()) {
                    ivType1.setImageResource(pokemon.typeSlot1.iconPokemonType())
                }
                if (pokemon.typeSlot2.isNotEmpty()) {
                    ivType2.setImageResource(pokemon.typeSlot2.iconPokemonType())
                    ivType2.isVisible = true
                } else {
                    ivType2.isVisible = false
                }
                layoutCard.setOnClickListener { onItemSelected(pokemon) }
            }
        }
    }
}