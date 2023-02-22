package com.example.rickandmortyplus.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.rickandmortyplus.ui.composables.CharacterCard


@Composable
fun ListCharactersScreen() {
    LazyColumn(){
        items(8){
            CharacterCard()
        }
    }
}

