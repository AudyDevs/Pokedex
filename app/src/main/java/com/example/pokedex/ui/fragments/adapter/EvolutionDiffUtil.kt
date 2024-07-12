package com.example.pokedex.ui.fragments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.domain.model.EvolutionModel

class EvolutionDiffUtil(
    private val oldList: List<EvolutionModel>,
    private val newList: List<EvolutionModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idPokemonOrigin == newList[newItemPosition].idPokemonOrigin
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}