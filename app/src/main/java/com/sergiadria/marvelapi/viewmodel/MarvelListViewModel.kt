package com.sergiadria.marvelapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergiadria.marvelapi.api.MarvelRepository
import com.sergiadria.marvelapi.model.MarvelCharacter
import kotlinx.coroutines.launch

class MarvelListViewModel : ViewModel() {
    private val marvelRepository = MarvelRepository()
    private val limit = 30

    private val _loading = MutableLiveData(true)
    val loading = _loading
    private val _marvelCharacters = MutableLiveData<List<MarvelCharacter>>()
    val marvelCharacters = _marvelCharacters
    private val _name = MutableLiveData<String>()
    val name = _name
    private val _offset = MutableLiveData<Int>(0)
    val offset = _offset

    init {
        viewModelScope.launch {
            getCharacters(limit, _offset.value ?: 0)
        }
    }

    // Funció per obtenir tots els personatges de Marvel
    suspend fun getCharacters(limit: Int, offset: Int) {
        _loading.value = true
        val response = marvelRepository.getCharacters(limit, offset)
        if (response.isSuccessful) {
            _marvelCharacters.value = response.body()
            _loading.value = false
        } else {
            _loading.value = false
        }
    }

    // Funció per obtenir els personatges de Marvel per nom
    fun searchCharactersByName() {
        viewModelScope.launch {
            if (_name.value.isNullOrEmpty()) {
                getCharacters(limit, _offset.value ?: 0)
                return@launch
            }

            _loading.value = true
            val response = marvelRepository.searchCharactersByName(_name.value ?: "")
            if (response.isSuccessful) {
                _marvelCharacters.value = response.body()
                _loading.value = false
            } else {
                _marvelCharacters.value = emptyList()
                _loading.value = false
            }
        }
    }

    fun onNameChange(name: String) {
        _name.value = name
    }

    fun nextCharacters() {
        _offset.value = (_offset.value?.plus(limit)) ?: (0 + limit)
        viewModelScope.launch {
            getCharacters(limit, _offset.value ?: 0)
        }
    }

    fun previusCharacters() {
        _offset.value = ((_offset.value?.minus(limit)) ?: 0).coerceAtLeast(0)
        viewModelScope.launch {
            getCharacters(limit, _offset.value ?: 0)
        }
    }
}
