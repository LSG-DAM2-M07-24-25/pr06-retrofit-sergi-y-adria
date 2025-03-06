package com.sergiadria.marvelapi.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment  // Importa aquesta línia
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sergiadria.marvelapi.model.MarvelCharacter
import androidx.compose.foundation.layout.Box  // Afegeix aquesta importació per al Box
import androidx.compose.foundation.lazy.items

@Composable
fun CharacterLazyColumn(characters: List<MarvelCharacter>, modifier: Modifier = Modifier, onClickCharacter: (MarvelCharacter) -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        if (characters.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(characters) { character ->
                    CharacterRow(
                        character = character,
                        modifier = Modifier.clickable {
                            onClickCharacter(character)
                        }
                    )
                }
            }
        } else {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CharacterGrid(characters: List<MarvelCharacter>, modifier: Modifier = Modifier, onClickCharacter: (MarvelCharacter) -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        if (characters.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp), // Minor size adaptatiu per diferents mides de pantalla
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(characters) { character ->
                    CharacterItem(
                        character = character,
                        modifier = Modifier.clickable {
                            onClickCharacter(character)
                        }
                    )
                }
            }
        } else {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
