package com.tim.pokemonapplication.presentation.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tim.pokemonapplication.AppModule
import com.tim.pokemonapplication.data.remote.responses.Pokemon
import com.tim.pokemonapplication.repository.PokemonRepository
import com.tim.pokemonapplication.util.Resource
import kotlinx.coroutines.launch

class PokemonDetailVM : ViewModel() {

    private lateinit var result: Pokemon

    fun getPokemonInfo(pokemonName: String): Pokemon {

        viewModelScope.launch {

            result = PokemonRepository(AppModule.providePokemonApi()).getPokemonInfo(pokemonName)
        }
        return result
    }
}