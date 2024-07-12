package com.example.pokedex.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.model.EvolutionModel

class EvolutionAdapter(
    private var evolutionList: List<EvolutionModel> = emptyList()
) :
    RecyclerView.Adapter<EvolutionViewHolder>() {

    fun updateList(list: List<EvolutionModel>) {
        val diff = EvolutionDiffUtil(evolutionList, list)
        val result = DiffUtil.calculateDiff((diff))
        evolutionList = list
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_evolution, parent, false)
        return EvolutionViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = evolutionList.size

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        holder.render(evolutionList[position])
    }
}