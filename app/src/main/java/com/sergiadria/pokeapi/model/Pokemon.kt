package com.sergiadria.pokeapi.model

data class Pokemon(
    val name: String,
    val url: String,
    val types: List<Type>?,
    val abilities: List<Ability>?
) {
    val id: String
        get() {
            return this.url.split("/").filter { it.isNotEmpty() }.last()
        }

    val imageUrl: String
        get() {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        }

    // Per obtenir els noms dels tipus
    val type: String?
        get() {
            return types?.joinToString(", ") { it.type.name }
        }

    // Per obtenir les habilitats
    val abilityList: String?
        get() {
            return abilities?.joinToString(", ") { it.ability.name } // Assumim que `ability` té un `name`
        }
}

data class Type(
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class Ability(
    val ability: AbilityInfo
)

data class AbilityInfo(
    val name: String
)
