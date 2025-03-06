package com.sergiadria.marvelapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.sergiadria.marvelapi.ui.theme.MarvelApiTheme
import com.sergiadria.marvelapi.view.CharacterView
import com.sergiadria.marvelapi.view.CharacterDetailScreen
import com.sergiadria.marvelapi.viewmodel.MarvelViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val marvelCharacterViewModel by viewModels<MarvelViewModel>()

        setContent {
            MarvelApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "character_list") {
                        composable("character_list") {
                            CharacterView(navController, marvelCharacterViewModel)
                        }
                        composable(
                            "character_detail/{characterName}",
                            arguments = listOf(navArgument("characterName") { type = NavType.StringType })
                        ) { backStackEntry ->

                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun MarvelApiPreview() {
    MarvelApiTheme {
        val navController = rememberNavController()
        CharacterView(
            viewModel = MarvelViewModel(),
            navController = navController
        )
    }
}
