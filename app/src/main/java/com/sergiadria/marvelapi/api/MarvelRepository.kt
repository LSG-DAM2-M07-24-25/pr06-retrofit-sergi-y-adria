package com.sergiadria.marvelapi.api

import com.sergiadria.marvelapi.PUBLIC_KEY
import com.sergiadria.marvelapi.model.ApiResponse
import com.sergiadria.marvelapi.model.MarvelCharacter
import retrofit2.Response

class MarvelRepository {

    private val apiInterface = APIInterface.create()
    private val timestamp = System.currentTimeMillis().toString()
    private val hash = APIInterface.generateHash()

    // Obtenir els personatges amb un límit específic
    suspend fun getCharacters(limit: Int): Response<List<MarvelCharacter>> {
        val apiResponse = apiInterface.getCharacters(limit, timestamp, PUBLIC_KEY, hash)
        return if (apiResponse.isSuccessful) {
            Response.success(apiResponse.body()?.data?.results)
        } else {
            Response.error(apiResponse.code(), apiResponse.errorBody()!!)
        }
    }

    // Obtenir un personatge per nom
    suspend fun getCharacterById(id: String): Response<MarvelCharacter> {
        val apiResponse = apiInterface.getCharacterById(id, timestamp, PUBLIC_KEY, hash)
        return if (apiResponse.isSuccessful) {
            Response.success(apiResponse.body()?.data?.results?.firstOrNull())
        } else {
            Response.error(apiResponse.code(), apiResponse.errorBody()!!)
        }
    }
}
