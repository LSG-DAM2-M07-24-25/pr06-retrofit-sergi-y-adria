package com.sergiadria.pokeapi.models

data class Pokemon(
    val name: String,
    val url: String,
    val id: String = url.split("/").filter { it.isNotEmpty() }.last(),
    val image: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
)