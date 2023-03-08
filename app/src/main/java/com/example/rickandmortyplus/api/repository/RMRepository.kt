package com.example.rickandmortyplus.api.repository

import com.apollographql.apollo.api.Response
import graphql.com.example.rickandmortyplus.CharactersListQuery

interface RMRepository {
    suspend fun getCharacters() : Response<CharactersListQuery.Data>
}