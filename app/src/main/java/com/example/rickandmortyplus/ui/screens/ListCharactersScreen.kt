package com.example.rickandmortyplus.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyplus.EMPTY_STR
import com.example.rickandmortyplus.api.ViewState
import com.example.rickandmortyplus.ui.composables.CharacterCard
import com.example.rickandmortyplus.ui.composables.ErrorDialog
import com.example.rickandmortyplus.ui.composables.LoadingWheel
import com.example.rickandmortyplus.viewmodel.CharacterViewModel

@Composable
fun ListCharactersScreen(
    characterViewModel: CharacterViewModel = hiltViewModel()
) {
    val charactersList = characterViewModel.charactersList.value
    val status = characterViewModel.charactersStatus.value

    LazyColumn() {
        if (charactersList != null) {
            items(charactersList) { character ->
                CharacterCard(character = character)
            }
        }
    }

    if (status is ViewState.Loading) {
        LoadingWheel()
    } else if (status is ViewState.Error) {
        ErrorDialog(
            message = status.message ?: EMPTY_STR,
            onErrorDialogDismiss = { })
    }
}

