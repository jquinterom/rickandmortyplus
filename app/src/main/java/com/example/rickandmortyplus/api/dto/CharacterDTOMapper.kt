package com.example.rickandmortyplus.api.dto

import com.example.rickandmortyplus.model.Character
import com.example.rickandmortyplus.model.Episode
import com.example.rickandmortyplus.model.Location
import graphql.com.example.rickandmortyplus.CharactersListQuery

class CharacterDTOMapper {

    private fun fromEpisodeQueryListToDomain(queryEpisode: CharactersListQuery.Episode?): Episode {
        return Episode(queryEpisode?.name)
    }

    private fun fromCharacterQueryListItemResultToDomain(characterQueryListResult: CharactersListQuery.Result?): Character {
        return Character(
            id = characterQueryListResult?.id,
            name = characterQueryListResult?.name,
            status = characterQueryListResult?.status,
            species = characterQueryListResult?.species,
            image = characterQueryListResult?.image,
            location = Location(characterQueryListResult?.location?.name),
            episode = characterQueryListResult?.episode?.map { fromEpisodeQueryListToDomain(it) },
            isFavorite = false
        )
    }

    fun fromCharacterQueryListResultToDomain(characters: CharactersListQuery.Characters?): List<Character>? {
        return characters?.results?.map { fromCharacterQueryListItemResultToDomain(it) }
    }
}