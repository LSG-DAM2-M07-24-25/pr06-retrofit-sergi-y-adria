package com.sergiadria.marvelapi.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sergiadria.marvelapi.components.CharacterDetail
import com.sergiadria.marvelapi.viewmodel.MarvelDetailViewModel

@Composable
fun CharacterDetailScreen(viewModel: MarvelDetailViewModel) {
    val character by viewModel.characterDetails.observeAsState()
    val loading by viewModel.loading.observeAsState()
    val error by viewModel.error.observeAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (loading!!) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (error != null) {
            Text(
                text = "Error: $error",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            character?.let { character ->
                CharacterDetail(character = character, onClickFavorite = {  })
            } ?: Text("No character found.")
        }
    }
}