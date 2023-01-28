package com.tim.pokemonapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.Fragments.PokemonListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonListFragment = PokemonListFragment()

        makeCurrentFragment(pokemonListFragment)
    }

    fun makeCurrentFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().apply {

            replace(R.id.MActivity_Fragment_Holder, fragment)
            commit()
        }
    }
}