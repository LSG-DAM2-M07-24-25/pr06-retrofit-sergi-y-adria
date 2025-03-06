package com.sergiadria.marvelapi.api

import com.sergiadria.marvelapi.BASE_URL
import com.sergiadria.marvelapi.PRIVATE_KEY
import com.sergiadria.marvelapi.PUBLIC_KEY
import com.sergiadria.marvelapi.model.ApiResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.MessageDigest

interface APIInterface {

    // Obtenir llistat de personatges Marvel amb límit
    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<ApiResponse>

    // Nou mètode per obtenir un personatge per nom
    @GET("characters")
    suspend fun getCharacterById(
        @Query("name") name: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<ApiResponse>

    companion object {
        // Crear una instància de l'APIInterface
        fun create(): APIInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIInterface::class.java)
        }

        // Generar el hash necessari per la consulta
        fun generateHash(): String {
            val timestamp = System.currentTimeMillis().toString()
            val input = "$timestamp$PRIVATE_KEY$PUBLIC_KEY"
            return md5(input)
        }

        // Funció per calcular el hash MD5
        private fun md5(string: String): String {
            val bytes = MessageDigest.getInstance("MD5").digest(string.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }
    }
}
