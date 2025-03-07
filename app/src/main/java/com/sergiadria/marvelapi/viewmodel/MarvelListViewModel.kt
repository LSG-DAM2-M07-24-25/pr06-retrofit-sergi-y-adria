package com.sergiadria.marvelapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiadria.marvelapi.api.MarvelRepository
import com.sergiadria.marvelapi.model.ApiResponse
import com.sergiadria.marvelapi.model.MarvelCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelListViewModel : ViewModel() {
    private val marvelRepository = MarvelRepository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _marvelCharacters = MutableLiveData<List<MarvelCharacter>>()
    val marvelCharacters = _marvelCharacters

    init {
        viewModelScope.launch {
            getCharacters(30)
        }
    }

    // Funció per obtenir tots els personatges de Marvel
    suspend fun getCharacters(limit: Int = 100) {
        val response = marvelRepository.getCharacters(limit)
        if (response.isSuccessful) {
            _marvelCharacters.value = response.body()
            _loading.value = false
        } else {
            _loading.value = false
        }
    }
}
