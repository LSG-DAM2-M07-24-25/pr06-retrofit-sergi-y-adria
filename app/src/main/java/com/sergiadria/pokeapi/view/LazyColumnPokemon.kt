package com.sergiadria.pokeapi.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import com.sergiadria.pokeapi.model.Pokemon

@Composable
fun PokemonList(navController: NavController, pokemons: List<Pokemon>, modifier: Modifier = Modifier) {
    // Comprova si la llista no està buida
    if (pokemons.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.fillMaxSize().padding(8.dp)
        ) {
            // Itera sobre la llista de pokemons i renderitza cada element
            items(pokemons) { pokemon ->
                PokemonItem(
                    pokemon = pokemon,
                    modifier = Modifier.clickable {
                        navController.navigate("pokemon_detail/${pokemon.name}")
                    }
                )
            }
        }
    } else {
        // Si la llista està buida, mostra un indicator de càrrega
        CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
fun PokemonGrid(navController: NavController, pokemons: List<Pokemon>, modifier: Modifier = Modifier) {
    if (pokemons.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            modifier = modifier.fillMaxSize().padding(8.dp)
        ) {
            // Corregir com es passa la llista a 'items'
            items(pokemons) { pokemon ->
                PokemonItem(
                    pokemon = pokemon,
                    modifier = Modifier.clickable {
                        navController.navigate("pokemon_detail/${pokemon.name}")
                    }
                )
            }
        }
    } else {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary)
    }
}
