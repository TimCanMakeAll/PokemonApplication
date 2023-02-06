package com.tim.pokemonapplication.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.presentation.adapters.RecyclerViewAdapter
import com.tim.pokemonapplication.presentation.models.PokemonListDataClass
import com.tim.pokemonapplication.presentation.models.PokemonListVM

open class PokemonListFragment : Fragment() {

    var namesList = mutableListOf<String>()
    var imagesLinksList = mutableListOf<String>()
    var numberList = mutableListOf<Int>()

    private val viewModel: PokemonListVM by activityViewModels()

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

        viewModel.pokemonList.observe(viewLifecycleOwner) {
            postToList(it)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.fr_PList_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerViewAdapter(namesList, imagesLinksList, numberList)
        recyclerView.adapter = adapter

        scrollListener(
            recyclerView,
            recyclerView.layoutManager as LinearLayoutManager,
            adapter,
        )
    }

    private fun scrollListener(
        recyclerView: RecyclerView,
        layoutManager: LinearLayoutManager,
        adapter: RecyclerViewAdapter,
    ) {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val loading = viewModel.isLoading

                val visibleItemCount: Int = layoutManager.childCount
                val pastVisibleItem: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                val totalItemNumber: Int = adapter.itemCount

                if (!loading.value){

                    if (visibleItemCount + pastVisibleItem >= totalItemNumber){
                        viewModel.pagination()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun addToList(name: String, imageLink: String, number: Int) {

        if (namesList.contains(name)){ return }

        namesList.add(name)
        imagesLinksList.add(imageLink)
        numberList.add(number)
    }

    private fun postToList(pokemonListDataClasses: List<PokemonListDataClass>) {

        var imageLink: String
        var number: Int

        if (pokemonListDataClasses.size < 20) {

            for (i in 0..pokemonListDataClasses.size - 1) {
                imageLink = pokemonListDataClasses[i].imageLink
                number = pokemonListDataClasses[i].id

                addToList(pokemonListDataClasses[i].name, imageLink, number)
                adapter.notifyItemInserted(adapter.itemCount)
            }
        } else {

            for (i in pokemonListDataClasses.size - 20..pokemonListDataClasses.size - 1) {
                imageLink = pokemonListDataClasses[i].imageLink
                number = pokemonListDataClasses[i].id

                addToList(pokemonListDataClasses[i].name, imageLink, number)
                adapter.notifyItemInserted(adapter.itemCount)
            }
        }
    }

    fun switchToDetailFragment(view: View, pokemonName: String, pokemonImageUrl: String) {

        val pokemonDetailObject = PokemonDetailFragment()

        val bundle = Bundle()
        bundle.putString("name", pokemonName)
        bundle.putString("imageUrl", pokemonImageUrl)
        pokemonDetailObject.arguments = bundle

        fragmentManager?.beginTransaction()?.replace(R.id.MActivity_fragmentHolder, pokemonDetailObject)?.commit()
    }
}

object PokemonListFragmentObject : PokemonListFragment()