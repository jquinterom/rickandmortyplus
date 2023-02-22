package com.example.rickandmortyplus.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title : String,
    val icon: ImageVector
) {
    object ListCharactersScreen : Destinations("listCharacters", "Characters", Icons.Filled.List)
    object FavoritesCharactersScreen: Destinations("favoritesCharacters", "Favorites", Icons.Filled.Favorite)
}