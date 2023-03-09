package com.example.rickandmortyplus.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.rickandmortyplus.EMPTY_CHARACTER
import com.example.rickandmortyplus.ui.composables.CharacterCard

@Composable
fun FavoritesCharactersScreen() {
    LazyColumn(){
        items(9){
            CharacterCard(EMPTY_CHARACTER)
        }
    }
}