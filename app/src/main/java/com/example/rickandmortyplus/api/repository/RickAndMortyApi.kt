package com.example.rickandmortyplus.api.repository

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.example.rickandmortyplus.URL_API_GRAPHQL
import okhttp3.OkHttpClient

class RickAndMortyApi {
    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }

        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.builder()
            .serverUrl(URL_API_GRAPHQL)
            .okHttpClient(okHttpClient)
            .build()
    }
}