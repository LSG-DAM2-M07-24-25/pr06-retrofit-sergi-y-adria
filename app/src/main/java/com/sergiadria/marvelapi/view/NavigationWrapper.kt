package com.sergiadria.marvelapi.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sergiadria.marvelapi.viewmodel.MarvelDetailViewModel
import com.sergiadria.marvelapi.viewmodel.MarvelListViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    val marvelListViewModel = MarvelListViewModel()

    NavHost(navController, startDestination = "character_list") {
        composable("character_list") {
            CharacterListView(navController = navController, viewModel = marvelListViewModel)
        }

        composable(
            "character/{characterID}",
            arguments = listOf(navArgument("characterID") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterID = backStackEntry.arguments?.getString("characterID")
            val marvelDetailViewModel = MarvelDetailViewModel(characterID ?: "")

            CharacterDetailScreen(viewModel = marvelDetailViewModel)
        }
    }
}