package com.example.rickandmortyplus.ui.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyplus.EMPTY_STR
import com.example.rickandmortyplus.RMApplication
import com.example.rickandmortyplus.api.ViewState
import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.model.Episode
import com.example.rickandmortyplus.model.Location
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
                val existsOnDB =
                    RMApplication.prefs.getFavoriteCharacter(characterId = character.id.toString())
                val isFavorite = existsOnDB != null
                CharacterCard(
                    character = Character(
                        character.id,
                        character.name,
                        character.status,
                        character.species,
                        character.image,
                        character.location,
                        character.episode,
                        isFavorite = isFavorite
                    ),
                    setFavorite = {characterViewModel.updateFavoriteCharacter(character = character)}
                )
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

