package com.sergiadria.marvelapi.view

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sergiadria.marvelapi.nav.Routes
import com.sergiadria.marvelapi.viewmodel.MarvelDetailViewModel
import com.sergiadria.marvelapi.viewmodel.MarvelListViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    val marvelListViewModel = MarvelListViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.CharacterList.route,
        modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)
    ) {
        composable(Routes.CharacterList.route) {
            CharacterListView(navController = navController, viewModel = marvelListViewModel)
        }

        composable(
            Routes.DetailsCharacter.route,
            arguments = listOf(
                navArgument("characterID") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val characterID = backStackEntry.arguments?.getString("characterID")
            val marvelDetailViewModel = MarvelDetailViewModel(characterID ?: "")

            CharacterDetailScreen(viewModel = marvelDetailViewModel)
        }
    }
}