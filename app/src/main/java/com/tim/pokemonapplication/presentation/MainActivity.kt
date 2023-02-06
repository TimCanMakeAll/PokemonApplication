package com.tim.pokemonapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.fragments.PokemonDetailFragment
import com.tim.pokemonapplication.presentation.fragments.PokemonListFragmentObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.MActivity_fragmentHolder, PokemonListFragmentObject).commit()
    }
}