package com.example.pokedex.ui.activities.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pokedex.domain.model.PokemonListModel

class MainDiffUtil(
    private val oldList: List<PokemonListModel>,
    private val newList: List<PokemonListModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}