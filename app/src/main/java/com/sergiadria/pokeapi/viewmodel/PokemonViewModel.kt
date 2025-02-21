package com.sergiadria.pokeapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiadria.pokeapi.api.Repository
import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel : ViewModel() {
    private val repository = Repository()

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse: LiveData<ApiResponse> = _apiResponse

    private val _pokemonDetails = MutableLiveData<Pokemon?>()
    val pokemonDetails: LiveData<Pokemon?> = _pokemonDetails

    fun getPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun getPokemonByName(name: String): Pokemon? {
        var foundPokemon: Pokemon? = null
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getPokemonByName(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    foundPokemon = response.body()
                    _pokemonDetails.value = foundPokemon
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
        return foundPokemon
    }
}
