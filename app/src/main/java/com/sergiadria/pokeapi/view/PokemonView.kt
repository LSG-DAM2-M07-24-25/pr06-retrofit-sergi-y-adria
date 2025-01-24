package com.sergiadria.pokeapi.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.viewmodel.PokemonViewModel

@Composable
fun PokemonView(viewModel: PokemonViewModel) {
    val showLoading: Boolean by viewModel.loading.observeAsState(true)
    val apiResponse: ApiResponse by viewModel.apiResponse.observeAsState(ApiResponse(0, null, null, emptyList()))
    viewModel.getPokemons()

    if (showLoading) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary
            )
        }
    } else {
        LazyColumn {
            items(apiResponse.pokemons) { pokemon ->
                PokemonItem(pokemon)
            }
        }
    }
}