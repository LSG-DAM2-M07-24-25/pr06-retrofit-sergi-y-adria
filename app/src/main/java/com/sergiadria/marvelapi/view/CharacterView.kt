package com.sergiadria.marvelapi.view

import CharacterGrid
import CharacterLazyColumn
import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sergiadria.marvelapi.model.ApiResponse
import com.sergiadria.marvelapi.model.MarvelApiData
import com.sergiadria.marvelapi.viewmodel.MarvelViewModel

@Composable
fun CharacterView(navController: NavController, viewModel: MarvelViewModel) {
    // Observant l'estat de la càrrega i la resposta de l'API
    val isLoading: Boolean by viewModel.loading.observeAsState(true)
    val apiResponse: ApiResponse by viewModel.apiResponse.observeAsState(ApiResponse(MarvelApiData(0, emptyList())))

    // Llamada per obtenir els personatges només una vegada quan el composable s'inicia
    LaunchedEffect(Unit) {
        // Es fa la crida només si la llista de personatges està buida
        if (apiResponse.data.results.isEmpty()) {
            viewModel.getCharacters()
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isWideScreen = maxWidth > 600.dp // Determinar si és una pantalla ampla (per exemple, tauletes o ordinadors)

        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.secondary, modifier = Modifier.align(
                Alignment.Center))
        } else {
            if (isWideScreen) {
                // Si la pantalla és ampla, mostrem una graella adaptativa
                CharacterGrid(
                    navController = navController,
                    characters = apiResponse.data.results,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                // Si la pantalla és petita, mostrem una llista vertical
                CharacterLazyColumn(
                    navController = navController,
                    characters = apiResponse.data.results,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

