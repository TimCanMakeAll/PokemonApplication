package com.tim.pokemonapplication.data.remote

import com.tim.pokemonapplication.data.remote.responses.Pokemon
import com.tim.pokemonapplication.data.remote.responses.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit1: Int,
        @Query("offset") offset1: Int,
    ) : PokemonList

//    @GET("pokemon/{name}")
//    suspend fun getPokemonInfo(
//        @Path("name") name: String
//    ): Pokemon

    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(
        @Path("id") id: Int,
    ): Pokemon

}