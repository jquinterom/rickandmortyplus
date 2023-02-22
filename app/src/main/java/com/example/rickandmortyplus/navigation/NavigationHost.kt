package com.example.rickandmortyplus.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyplus.navigation.Destinations.FavoritesCharactersScreen
import com.example.rickandmortyplus.navigation.Destinations.ListCharactersScreen
import com.example.rickandmortyplus.ui.screens.FavoritesCharactersScreen
import com.example.rickandmortyplus.ui.screens.ListCharactersScreen

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ListCharactersScreen.route) {
        composable(ListCharactersScreen.route) {
            ListCharactersScreen()
        }

        composable(
            FavoritesCharactersScreen.route,
        ) {
            FavoritesCharactersScreen()
        }
    }
}