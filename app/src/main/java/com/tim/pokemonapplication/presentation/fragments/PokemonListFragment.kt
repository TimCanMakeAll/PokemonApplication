package com.tim.pokemonapplication.presentation.fragments

import android.os.Bundle
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

        val viewModel: PokemonListVM by activityViewModels()
        viewModel.curPage = 1
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            postToList(it)
            viewModel.pagination()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.fr_PList_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerViewAdapter(namesList, imagesLinksList, numberList)
        recyclerView.adapter = adapter

        //recyclerViewDetector(recyclerView)
    }

    /*private fun recyclerViewDetector(recyclerView: RecyclerView){

        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    Log.d("MyTAG", "Load new list")
                    recycler.removeOnScrollListener(scrollListener)
                }
            }
        }
        recycler.addOnScrollListener(scrollListener)
    }*/

    private fun addToList(name: String, imageLink: String, number: Int) {

        namesList.add(name)
        imagesLinksList.add(imageLink)
        numberList.add(number)
    }

    private fun postToList(pokemonListDataClasses: List<PokemonListDataClass>) {

        var imageLink: String
        var number: Int

        if (pokemonListDataClasses.size < 20){

            for (i in 0 ..pokemonListDataClasses.size - 1) {
                imageLink = pokemonListDataClasses[i].imageLink
                number = pokemonListDataClasses[i].id

                addToList(pokemonListDataClasses[i].name, imageLink, number)
                adapter.notifyItemInserted(adapter.itemCount)
            }
        } else {

            for (i in pokemonListDataClasses.size - 20 ..pokemonListDataClasses.size - 1) {
                imageLink = pokemonListDataClasses[i].imageLink
                number = pokemonListDataClasses[i].id

                addToList(pokemonListDataClasses[i].name, imageLink, number)
                adapter.notifyItemInserted(adapter.itemCount)
            }
        }
    }

    fun switchToDetailFragment(view: View) {

        Navigation.findNavController(view).navigate(R.id.pokemonDetailFragment)
    }
}