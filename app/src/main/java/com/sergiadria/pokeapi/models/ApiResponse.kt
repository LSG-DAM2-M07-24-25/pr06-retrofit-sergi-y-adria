package com.sergiadria.pokeapi.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    @SerializedName("results")
    val pokemons: List<Pokemon>
)