package com.example.rickandmortyplus.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyplus.EMPTY_CHARACTER
import com.example.rickandmortyplus.ui.composables.CharacterCard
import com.example.rickandmortyplus.viewmodel.CharacterViewModel

@Composable
fun FavoritesCharactersScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    LazyColumn(){
        items(9){
            CharacterCard(EMPTY_CHARACTER) { }
        }
    }
}