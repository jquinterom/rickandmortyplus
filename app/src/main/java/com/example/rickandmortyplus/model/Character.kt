package com.example.rickandmortyplus.model

data class Character(
    val id: String?,
    val name: String?,
    val status: String?,
    val species: String?,
    val image: String?,
    val location: Location?,
    val episode: List<Episode>?,
    val isFavorite: Boolean
)