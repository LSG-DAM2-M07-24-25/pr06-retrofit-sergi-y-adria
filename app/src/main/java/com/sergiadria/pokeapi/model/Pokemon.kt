package com.sergiadria.pokeapi.model

data class Pokemon(
    val name: String,
    val url: String
) {
    val id: String
        get() {
            return this.url.split("/").filter { it.isNotEmpty() }.last()
        }

    val imageUrl: String
        get() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }
}