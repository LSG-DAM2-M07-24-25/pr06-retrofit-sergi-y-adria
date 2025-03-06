package com.sergiadria.marvelapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergiadria.marvelapi.api.Repository
import com.sergiadria.marvelapi.model.ApiResponse
import com.sergiadria.marvelapi.model.MarvelCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelViewModel : ViewModel() {
    private val repository = Repository()
    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse = _apiResponse
    private val _characterDetails = MutableLiveData<MarvelCharacter>()
    val characterDetails = _characterDetails
    private val _characterByName = MutableLiveData<MarvelCharacter>()
    val characterByName = _characterByName

    // Funció per obtenir tots els personatges de Marvel
    fun getCharacters(limit: Int = 100) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacters(limit)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("MarvelViewModel", "Characters obtenidos!")
                    _apiResponse.value = response.body()
                    _loading.value = false
                } else {
                    Log.e("MarvelViewModel", response.message())
                    _loading.value = false
                }
            }
        }
    }

    // Funció per obtenir un personatge per nom
    fun getCharacterById(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacterById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("MarvelViewModel", "Character ID: $id obtenido!")
                    _characterByName.value = response.body()?.data?.results?.firstOrNull()
                    _loading.value = false
                } else {
                    Log.e("MarvelViewModel", response.message())
                    _loading.value = false
                }
            }
        }
    }
}
