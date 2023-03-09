package com.example.rickandmortyplus.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.rickandmortyplus.api.ViewState
import com.example.rickandmortyplus.api.dto.CharacterDTOMapper
import com.example.rickandmortyplus.api.repository.RMRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import graphql.com.example.rickandmortyplus.CharactersListQuery
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.rickandmortyplus.model.*

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: RMRepository
) : ViewModel() {

    var charactersStatus =
        mutableStateOf<ViewState<Response<CharactersListQuery.Data>>?>(null)

    var charactersList = mutableStateOf<List<Character>?>(null)
        private set

    private fun getCharacters() = viewModelScope.launch {
        charactersStatus.value = ViewState.Loading()
        try {
            val response = repository.getCharacters()
            charactersStatus.value = ViewState.Success(response)
            charactersList.value =
                CharacterDTOMapper().fromCharacterQueryListResultToDomain(
                    (charactersStatus.value as ViewState.Success<Response<CharactersListQuery.Data>>)
                        .value?.data?.characters
                )
        } catch (e: ApolloException) {
            Log.e("ApolloException", "Failure", e)
            charactersStatus.value = ViewState.Error("Error fetching characters")
        }
    }

    init {
        getCharacters()
    }
}