package com.example.pokedex.ui.activities.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.di.Constants.ONE_COLUMN_LIST
import com.example.pokedex.domain.model.PokemonListModel

class MainAdapter(
    private var pokemonList: List<PokemonListModel> = emptyList(),
    private val onItemSelected: (PokemonListModel) -> Unit
) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var viewType = ONE_COLUMN_LIST

    fun updateList(list: List<PokemonListModel>) {
        val diff = MainDiffUtil(pokemonList, list)
        val result = DiffUtil.calculateDiff((diff))
        pokemonList = list
        result.dispatchUpdatesTo(this)
    }

    fun updateViewType(numColumns: Int) {
        viewType = numColumns
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemLayout = if (viewType == ONE_COLUMN_LIST) {
            R.layout.item_pokemon_one_column
        } else {
            R.layout.item_pokemon_two_columns
        }

        val layoutInflater =
            LayoutInflater.from(parent.context)
                .inflate(itemLayout, parent, false)
        return MainViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(pokemonList[position], getItemViewType(position), onItemSelected)
    }
}