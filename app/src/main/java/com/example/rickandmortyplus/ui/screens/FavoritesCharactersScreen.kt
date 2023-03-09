package com.example.rickandmortyplus.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.model.Episode
import com.example.rickandmortyplus.model.Location
import com.example.rickandmortyplus.ui.composables.CharacterCard

@Composable
fun FavoritesCharactersScreen() {
    LazyColumn(){
        items(9){
            CharacterCard(Character("1", "", "","","", Location(""), listOf<Episode>()))
        }
    }
}