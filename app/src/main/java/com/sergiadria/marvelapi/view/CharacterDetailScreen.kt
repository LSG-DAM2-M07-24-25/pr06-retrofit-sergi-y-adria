package com.sergiadria.marvelapi.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sergiadria.marvelapi.model.MarvelCharacter

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetailScreen(character: MarvelCharacter) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Ajust de la mida de la imatge
        val imageModifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)

        GlideImage(
            model = character.imageUrl,
            contentDescription = character.name,
            modifier = imageModifier
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
