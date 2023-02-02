package com.tim.pokemonapplication.presentation.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.adapters.RecyclerViewAdapter
import com.tim.pokemonapplication.presentation.models.PokemonListDataClass
import com.tim.pokemonapplication.presentation.models.PokemonListVM
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

class PokemonListFragment : Fragment() {

    var namesList = mutableListOf<String>()
    var imagesLinksList = mutableListOf<String>()
    var numberList = mutableListOf<Int>()

    lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model: PokemonListVM by activityViewModels()

        model.pokemonList.observe(viewLifecycleOwner) {
            postToList(it)
            model.pagination()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.fr_PList_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerViewAdapter(namesList, imagesLinksList, numberList)
        recyclerView.adapter = adapter
    }

    fun addToList(name: String, imageLink: String, number: Int) {

        namesList.add(name)
        imagesLinksList.add(imageLink)
        numberList.add(number)
    }

    fun postToList(pokemonListDataClasses: List<PokemonListDataClass>) {

        var imageLink: String
        var number: Int

        for (i in 0..pokemonListDataClasses.size - 1) {

            imageLink = pokemonListDataClasses[i].imageLink

            number = pokemonListDataClasses[i].id

            addToList(pokemonListDataClasses[i].name, imageLink, number)
            adapter.notifyItemInserted(adapter.itemCount)
        }
    }

    fun switchToDetailFragment(view: View) {

        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment)
    }
}
