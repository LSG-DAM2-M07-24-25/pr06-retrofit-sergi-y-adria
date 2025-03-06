package com.sergiadria.marvelapi.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
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
