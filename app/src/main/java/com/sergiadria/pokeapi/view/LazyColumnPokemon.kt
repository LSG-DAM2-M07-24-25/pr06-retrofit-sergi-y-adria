package com.sergiadria.pokeapi.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sergiadria.pokeapi.model.Pokemon

@Composable
fun PokemonList(pokemons: List<Pokemon>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonListItem(pokemon)
        }
    }
}

@Composable
fun PokemonGrid(pokemons: List<Pokemon>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        items(pokemons) { pokemon ->
            PokemonGridItem(pokemon)
        }
    }
}
