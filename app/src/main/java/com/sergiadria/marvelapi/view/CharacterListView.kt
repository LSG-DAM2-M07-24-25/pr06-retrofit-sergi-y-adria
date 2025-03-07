package com.sergiadria.marvelapi.view

import com.sergiadria.marvelapi.components.CharacterGrid
import com.sergiadria.marvelapi.components.CharacterLazyColumn
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sergiadria.marvelapi.R
import com.sergiadria.marvelapi.model.MarvelCharacter
import com.sergiadria.marvelapi.nav.Routes
import com.sergiadria.marvelapi.viewmodel.MarvelListViewModel

@Composable
fun CharacterListView(navController: NavController, viewModel: MarvelListViewModel) {
    // Observant l'estat de la càrrega i la resposta de l'API
    val isLoading: Boolean by viewModel.loading.observeAsState(true)
    val marvelCharacters: List<MarvelCharacter> by viewModel.marvelCharacters.observeAsState(emptyList())

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(3f))
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.weight(1f))
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isWideScreen =
                maxWidth > 600.dp // Determinar si és una pantalla ampla (per exemple, tauletes o ordinadors)

            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                if (isWideScreen) {
                    // Si la pantalla és ampla, mostrem una graella adaptativa
                    CharacterGrid(
                        onClickCharacter = { character ->
                            navController.navigate(Routes.DetailsCharacter.createRoute(character.id.toString()))
                        },
                        characters = marvelCharacters,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    // Si la pantalla és petita, mostrem una llista vertical
                    CharacterLazyColumn(
                        onClickCharacter = { character ->
                            navController.navigate(Routes.DetailsCharacter.createRoute(character.id.toString()))
                        },
                        characters = marvelCharacters,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

