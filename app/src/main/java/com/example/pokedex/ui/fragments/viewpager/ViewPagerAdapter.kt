package com.example.pokedex.ui.fragments.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokedex.ui.fragments.AboutFragment
import com.example.pokedex.ui.fragments.DefenseFragment
import com.example.pokedex.ui.fragments.EvolutionFragment
import com.example.pokedex.ui.fragments.StatsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment()
            1 -> StatsFragment()
            2 -> DefenseFragment()
            3 -> EvolutionFragment()
            else -> AboutFragment()
        }
    }
}