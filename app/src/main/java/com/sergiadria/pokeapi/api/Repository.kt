package com.sergiadria.pokeapi.api

class Repository {

    val apiInterface = APIInterface.create()

    suspend fun getPokemons() = apiInterface.getPokemons()
}