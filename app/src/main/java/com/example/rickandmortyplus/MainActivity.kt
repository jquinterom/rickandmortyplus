package com.example.rickandmortyplus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyplus.navigation.BottomNavigationBar
import com.example.rickandmortyplus.navigation.Destinations
import com.example.rickandmortyplus.navigation.NavigationHost
import com.example.rickandmortyplus.ui.theme.RickAndMortyPlusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyPlusTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        Destinations.ListCharactersScreen,
        Destinations.FavoritesCharactersScreen
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }
    ){
        NavigationHost(navController)
    }
}
