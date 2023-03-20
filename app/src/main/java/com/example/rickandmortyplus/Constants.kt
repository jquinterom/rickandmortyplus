package com.example.rickandmortyplus

import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.model.Episode
import com.example.rickandmortyplus.model.Location

const val URL_API_GRAPHQL = "https://rickandmortyapi.com/graphql"
const val EMPTY_STR = ""
const val STATUS_ALIVE = "Alive"
val EMPTY_CHARACTER = Character(
    "1",
    "Fake Name",
    "Alive",
    "Human",
    "https://rickandmortyapi.com/api/character/avatar/200.jpeg",
    Location("Earth"),
    listOf(
        Episode(name = "First Episode")
    ),
    false
)
