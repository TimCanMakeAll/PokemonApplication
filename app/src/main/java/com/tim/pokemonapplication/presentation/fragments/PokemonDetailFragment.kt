package com.tim.pokemonapplication.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.tim.pokemonapplication.R

class PokemonDetailFragment : Fragment() {

    private lateinit var exitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        exitButton = view.findViewById(R.id.exit_button)

        exitButton.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.pokemonListFragment)
        }
        return view
    }
}