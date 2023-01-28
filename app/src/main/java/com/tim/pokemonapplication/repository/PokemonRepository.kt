package com.tim.pokemonapplication.repository
import com.tim.pokemonapplication.data.remote.PokemonApi
import com.tim.pokemonapplication.data.remote.responses.Pokemon
import com.tim.pokemonapplication.data.remote.responses.PokemonList
import com.tim.pokemonapplication.util.Resource

class PokemonRepository constructor(
    private val Api: PokemonApi
) {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {

        val response = try {

            Api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An unknown error")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {

        val response = try {

            Api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An unknown error")
        }
        return Resource.Success(response)
    }
}