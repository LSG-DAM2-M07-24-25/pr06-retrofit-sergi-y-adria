package com.sergiadria.marvelapi.api

import com.sergiadria.marvelapi.PUBLIC_KEY
import com.sergiadria.marvelapi.model.ApiResponse
import retrofit2.Response

class Repository {

    private val apiInterface = APIInterface.create()
    private val timestamp = System.currentTimeMillis().toString()
    private val hash = APIInterface.generateHash()

    // Obtenir els personatges amb un límit específic
    suspend fun getCharacters(limit: Int): Response<ApiResponse> {
        return apiInterface.getCharacters(limit, timestamp, PUBLIC_KEY, hash)
    }

    // Obtenir un personatge per nom
    suspend fun getCharacterById(id: String): Response<ApiResponse> {
        return apiInterface.getCharacterById(id, timestamp, PUBLIC_KEY, hash)
    }
}
