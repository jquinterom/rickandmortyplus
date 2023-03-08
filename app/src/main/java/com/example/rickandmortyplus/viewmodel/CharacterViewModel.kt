package com.example.rickandmortyplus.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.example.rickandmortyplus.api.ViewState
import com.example.rickandmortyplus.api.repository.RMRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import graphql.com.example.rickandmortyplus.CharactersListQuery
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: RMRepository
) : ViewModel() {

    var charactersList = mutableStateOf<ViewState<Response<CharactersListQuery.Data>>?>(null)
        private set

    private fun getCharacters() = viewModelScope.launch {
        charactersList.value = ViewState.Loading()
        try {
            val response = repository.getCharacters()
            charactersList.value = ViewState.Success(response)
        } catch (e: ApolloException) {
            Log.e("ApolloException", "Failure", e)
            charactersList.value = ViewState.Error("Error fetching characters")
        }
    }

    init {
        getCharacters()
    }
}