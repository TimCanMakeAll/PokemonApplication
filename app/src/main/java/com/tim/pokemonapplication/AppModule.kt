package com.tim.pokemonapplication

import com.tim.pokemonapplication.data.remote.PokemonApi
import com.tim.pokemonapplication.repository.PokemonRepository
import com.tim.pokemonapplication.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    fun providePokemonRepository(
        Api: PokemonApi
    ) = PokemonRepository(Api)

    fun providePokemonApi(): PokemonApi {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokemonApi::class.java)
    }
}