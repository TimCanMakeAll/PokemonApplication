package com.tim.pokemonapplication.presentation.models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tim.pokemonapplication.AppModule
import com.tim.pokemonapplication.repository.PokemonRepository
import com.tim.pokemonapplication.util.Constants.PAGE_SIZE
import com.tim.pokemonapplication.util.Resource
import kotlinx.coroutines.launch
import java.util.*

class PokemonListVM : ViewModel() {

    var curPage = 0;

    var pokemonList = MutableLiveData<List<PokemonListDataClass>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        pagination()
    }

    fun pagination() {

        viewModelScope.launch {

            isLoading.value = true
            val result = PokemonRepository(AppModule.providePokemonApi()).getPokemonList(
                PAGE_SIZE,
                curPage * PAGE_SIZE
            )

            when (result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count

                    val pokemonEntry = result.data.results.mapIndexed { index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListDataClass(number.toInt(), entry.name.toUpperCase(Locale.ROOT), url)
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value = pokemonList.value?.plus(pokemonEntry)

                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }
}
