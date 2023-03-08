package com.example.rickandmortyplus.di

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.rickandmortyplus.api.repository.RMRepository
import com.example.rickandmortyplus.api.repository.RickAndMortyApi
import graphql.com.example.rickandmortyplus.CharactersListQuery
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: RickAndMortyApi
) : RMRepository {
    override suspend fun getCharacters(): Response<CharactersListQuery.Data> {
        return charactersApi.getApolloClient().query(CharactersListQuery()).await()
    }

}