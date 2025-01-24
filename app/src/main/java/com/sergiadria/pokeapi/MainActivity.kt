package com.sergiadria.pokeapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergiadria.pokeapi.models.ApiResponse
import com.sergiadria.pokeapi.models.Pokemon
import com.sergiadria.pokeapi.ui.theme.PokeApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val apiResponse = ApiResponse(1, "", "", listOf(Pokemon("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")))
        val pokemon = apiResponse.pokemons.first()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    DebugPokemon(paddingValues, pokemon)
                }
            }
        }
    }
}

@Composable
fun DebugPokemon(paddingValues: PaddingValues, pokemon: Pokemon) {
    Column (Modifier.padding(paddingValues)) {
        Text(pokemon.name)
        Text(pokemon.url)
        Text(pokemon.id)
        Text(pokemon.image)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokeApiTheme {
        DebugPokemon(
            paddingValues = PaddingValues(10.dp),
            pokemon = Pokemon("Bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/")
        )
    }
}