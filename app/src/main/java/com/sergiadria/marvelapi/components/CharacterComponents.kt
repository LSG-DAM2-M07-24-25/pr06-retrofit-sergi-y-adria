package com.sergiadria.marvelapi.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.sergiadria.marvelapi.model.MarvelCharacter

@Composable
fun CharacterRow(character: MarvelCharacter, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ajust de la mida de la imatge segons la mida de la pantalla
            val imageSize = if (LocalConfiguration.current.screenWidthDp > 600) 100.dp else 80.dp

            SubcomposeAsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(imageSize),
                loading = { CircularProgressIndicator() },
                error = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )

            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun CharacterItem(character: MarvelCharacter, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
       // Ajust de la mida de la imatge segons la mida de la pantalla
        val imageSize = if (LocalConfiguration.current.screenWidthDp > 600) 100.dp else 80.dp

        SubcomposeAsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(imageSize)
                .align(Alignment.CenterHorizontally),
            loading = { CircularProgressIndicator() },
            error = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp)
                )
            }
        )
    }
}

@Composable
fun CharacterDetail(character: MarvelCharacter, onClickFavorite: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Row {
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onClickFavorite) {
                Text("Favorite")
            }
        }

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
