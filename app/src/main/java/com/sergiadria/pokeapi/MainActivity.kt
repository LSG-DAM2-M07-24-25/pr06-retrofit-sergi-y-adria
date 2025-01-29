package com.sergiadria.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergiadria.pokeapi.model.ApiResponse
import com.sergiadria.pokeapi.model.Pokemon
import com.sergiadria.pokeapi.ui.theme.PokeApiTheme
import com.sergiadria.pokeapi.view.PokemonView
import com.sergiadria.pokeapi.viewmodel.PokemonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonViewModel by viewModels<PokemonViewModel>()

        setContent {
            PokeApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PokemonView(viewModel = pokemonViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokeApiPreview() {
    PokeApiTheme {
        PokemonView(viewModel = PokemonViewModel())
    }
}