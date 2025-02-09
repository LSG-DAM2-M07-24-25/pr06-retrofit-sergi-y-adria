package com.sergiadria.pokeapi

import com.sergiadria.pokeapi.view.PokemonView
import android.annotation.SuppressLint
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.sergiadria.pokeapi.ui.theme.PokeApiTheme
import com.sergiadria.pokeapi.view.PokemonDetailScreen
import com.sergiadria.pokeapi.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonViewModel by viewModels<PokemonViewModel>()

        setContent {
            PokeApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "pokemon_list") {
                        composable("pokemon_list") {
                            PokemonView(navController, pokemonViewModel)
                        }
                        composable(
                            "pokemon_detail/{pokemonName}",
                            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: "Sense nom"
                            val pokemon = pokemonViewModel.getPokemonByName(pokemonName) // Necessites obtenir el Pokémon per nom
                            PokemonDetailScreen(pokemon = pokemon)
                        }
                    }
                }
            }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PokeApiPreview() {
    PokeApiTheme {
        val navController = rememberNavController()
        PokemonView(
            viewModel = PokemonViewModel(),
            navController = navController
        )
    }
}
