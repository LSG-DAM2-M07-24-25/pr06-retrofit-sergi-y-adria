package com.sergiadria.marvelapi.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val data: MarvelApiData
)

data class MarvelApiData(
    val count: Int,
    val results: List<MarvelCharacter>
)
