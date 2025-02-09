package com.sergiadria.pokeapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergiadria.pokeapi.api.Repository
import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse = _apiResponse
    private val _pokemonDetails = MutableLiveData<Pokemon>()
    val pokemonDetails = _pokemonDetails

    // Funció per obtenir tots els pokémons
    fun getPokemons() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPokemons(10000)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _apiResponse.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    // Funció per obtenir un Pokémon per nom
    fun getPokemonByName(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getPokemonByName(name) // Hauries de tenir aquest mètode a l'API
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _pokemonDetails.value = response.body()
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }
}
