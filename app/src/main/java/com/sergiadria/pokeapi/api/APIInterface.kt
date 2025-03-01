package com.sergiadria.pokeapi.api

import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.model.Pokemon
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("pokemon")
    suspend fun getPokemons(@Query("limit") limit: Int): Response<ApiResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Response<Pokemon> // Corregit l'ús del Path

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}
