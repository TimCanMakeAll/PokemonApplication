package com.tim.pokemonapplication.presentation.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.MainActivity
import com.tim.pokemonapplication.presentation.adapters.RecyclerViewAdapter

class PokemonListFragment : Fragment() {

    private var imagesList = mutableListOf<Int>()
    private var namesList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postToList()

        val recyclerView: RecyclerView = view.findViewById(R.id.fr_PList_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = RecyclerViewAdapter(namesList, imagesList)
    }

    private fun addToList(name: String, image: Int){

        namesList.add(name)
        imagesList.add(image)
    }

    private fun postToList(){

        for (i in 1..20){

            addToList("name $i", R.mipmap.ic_launcher)
        }
    }

    fun switchToDetailFragment(view: View){

        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment)
    }
}