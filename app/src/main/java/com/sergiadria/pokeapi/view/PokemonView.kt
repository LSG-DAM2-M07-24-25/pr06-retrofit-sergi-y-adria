package com.sergiadria.pokeapi.view

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.viewmodel.PokemonViewModel

@Composable
fun PokemonView(navController: NavController, viewModel: PokemonViewModel) {
    // Observant l'estat de la càrrega i la resposta de l'API
    val showLoading: Boolean by viewModel.loading.observeAsState(true)
    val apiResponse: ApiResponse by viewModel.apiResponse.observeAsState(ApiResponse(0, null, null, emptyList()))

    // Llamada per obtenir els Pokémon només una vegada quan el composable s'inicia
    LaunchedEffect(Unit) {
        // Es fa la crida només si la llista de pokemons està buida
        if (apiResponse.pokemons.isEmpty()) {
            viewModel.getPokemons()
        }
    }

    BoxWithConstraints {
        val isWideScreen = maxWidth > 600.dp

        if (showLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
        } else {
            if (isWideScreen) {
                // Passar la llista de pokemons i modificar adequadament
                PokemonGrid(
                    navController = navController,
                    pokemons = apiResponse.pokemons, // Passant la llista de pokemons
                    modifier = Modifier.fillMaxSize() // Assignant un modificador per al disseny
                )
            } else {
                // Passar la llista de pokemons a la funció PokemonList
                PokemonList(navController = navController, pokemons = apiResponse.pokemons)
            }
        }
    }
}
