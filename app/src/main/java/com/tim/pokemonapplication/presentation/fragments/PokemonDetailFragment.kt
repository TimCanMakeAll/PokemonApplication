package com.tim.pokemonapplication.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import com.tim.pokemonapplication.R
import com.tim.pokemonapplication.data.remote.responses.Pokemon
import com.tim.pokemonapplication.presentation.models.PokemonDetailVM
import com.tim.pokemonapplication.util.Resource

open class PokemonDetailFragment : Fragment() {

    private lateinit var exitButton: Button
    lateinit var nameTextView: TextView
    lateinit var typeTextView: TextView
    lateinit var heightTextView: TextView
    lateinit var weightTextView: TextView
    lateinit var imageImageView: ImageView

    private val viewModel: PokemonDetailVM by activityViewModels()

    @SuppressLint("MissingInflatedId", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        nameTextView = view.findViewById(R.id.fr_PDetail_name_textView) as TextView
        typeTextView = view.findViewById(R.id.fr_PDetail_type_data_textView) as TextView
        heightTextView = view.findViewById(R.id.fr_PDetail_height_data_textView) as TextView
        weightTextView = view.findViewById(R.id.fr_PDetail_weight_data_textView) as TextView
        imageImageView = view.findViewById(R.id.fr_PDetail_pokemon_imageView)

        exitButton = view.findViewById(R.id.exit_button)

        exitButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.MActivity_fragmentHolder, PokemonListFragmentObject)?.commit()
        }

        val args = this.arguments
        val pokemonName = args?.get("name").toString()
        val pokemonImageUrl = args?.get("imageUrl").toString()

        nameTextView.text = pokemonName
        Picasso.get().load(pokemonImageUrl).fit().into(imageImageView)

        //val poomonInfo = viewModel.getPokemonInfo("bulbasaur")
        //val pokemonInfo = viewModel.getPokemonInfo("bulbasaur")
        //Log.d("TestTest", "${pokemonInfo.data} + 1")

        return view
    }
}