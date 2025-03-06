package com.sergiadria.marvelapi.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.sergiadria.marvelapi.model.MarvelCharacter
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
            character?.let { CharacterDetail(character = it) } ?: Text("No character found.")
        }
    }
}

@Composable
fun CharacterDetail(character: MarvelCharacter) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Ajust de la mida de la imatge
        val imageModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)

        SubcomposeAsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = imageModifier,
            loading = { CircularProgressIndicator() }
        )

        // Descripció amb ajust per pantalles petites i grans
        Text(
            text = character.description.ifEmpty { "No description available." },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Comics: ${character.comics.items.joinToString(", ") { it.name }}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Series: ${character.series.items.joinToString(", ") { it.name }}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Stories: ${character.stories.items.joinToString(", ") { it.name }}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
