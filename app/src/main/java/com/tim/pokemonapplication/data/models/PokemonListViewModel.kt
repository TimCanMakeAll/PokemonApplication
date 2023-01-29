package com.tim.pokemonapplication.data.models

import androidx.lifecycle.ViewModel
import com.tim.pokemonapplication.repository.PokemonRepository

class PokemonListViewModel(
    private val repository: PokemonRepository
): ViewModel() {
}