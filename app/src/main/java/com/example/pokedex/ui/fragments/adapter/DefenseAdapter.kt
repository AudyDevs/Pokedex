package com.example.pokedex.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R

class DefenseAdapter(
    private var defensesList: List<String> = emptyList()
) :
    RecyclerView.Adapter<DefenseViewHolder>() {

    fun updateList(list: List<String>) {
        val diff = DefenseDiffUtil(defensesList, list)
        val result = DiffUtil.calculateDiff((diff))
        defensesList = list
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefenseViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_defense, parent, false)
        return DefenseViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int = defensesList.size

    override fun onBindViewHolder(holder: DefenseViewHolder, position: Int) {
        holder.render(defensesList[position])
    }
}