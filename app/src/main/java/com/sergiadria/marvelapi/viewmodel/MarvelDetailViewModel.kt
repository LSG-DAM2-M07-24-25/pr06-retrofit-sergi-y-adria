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

class MarvelDetailViewModel(id: Int) : ViewModel() {
    private val marvelRepository = MarvelRepository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _error = MutableLiveData<String>()
    val error = _error
    private val _characterDetails = MutableLiveData<MarvelCharacter>()
    val characterDetails = _characterDetails

    init {
        viewModelScope.launch {
            getCharacterById(id)
        }
    }

    // Funció per obtenir un personatge per nom
    suspend fun getCharacterById(id: Int) {
        val response = marvelRepository.getCharacterById(id)
        if (response.isSuccessful) {
            _characterDetails.value = response.body()
            _loading.value = false
        } else {
            _loading.value = false
            _error.value = response.message()
        }
    }
}
