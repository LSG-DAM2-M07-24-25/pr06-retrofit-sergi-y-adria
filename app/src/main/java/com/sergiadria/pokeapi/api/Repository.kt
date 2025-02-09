package com.sergiadria.pokeapi.api

import com.sergiadria.pokeapi.model.Pokemon
import retrofit2.Response
import com.sergiadria.pokeapi.api.APIInterface

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getPokemons(limit: Int) = apiInterface.getPokemons(limit)

    // Funció suspendida per obtenir el Pokémon per nom
    suspend fun getPokemonByName(name: String): Response<Pokemon> {
        return apiInterface.getPokemonByName(name)
    }
}
