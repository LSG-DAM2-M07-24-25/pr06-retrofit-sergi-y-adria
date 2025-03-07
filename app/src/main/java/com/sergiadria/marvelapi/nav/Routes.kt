package com.sergiadria.marvelapi.nav

sealed class Routes(val route: String){
    object CharacterList: Routes("character_list")

    object DetailsCharacter: Routes("character/{characterID}") {
        fun createRoute(characterID: String) = "character/$characterID"
    }
}