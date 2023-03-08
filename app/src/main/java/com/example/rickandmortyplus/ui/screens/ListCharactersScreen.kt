package com.example.rickandmortyplus.ui.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyplus.ui.composables.CharacterCard
import com.example.rickandmortyplus.viewmodel.CharacterViewModel

@Composable
fun ListCharactersScreen(
    characterViewModel : CharacterViewModel = hiltViewModel()
) {
    val charactersList  = characterViewModel.charactersList.value
    Log.d("responseList", charactersList?.value.toString())


    LazyColumn() {
        if (charactersList?.value != null) {
            for (character in charactersList.value.data?.characters?.results!!) {
                Log.d("character", character.toString())
            }
            items(charactersList.value.data?.characters?.results!!.size){
                CharacterCard()
            }
        }
        /*
        items(8) {
            CharacterCard()
        }
         */
    }
}

