package com.example.rickandmortyplus.database

import android.content.Context

class Prefs(val context: Context) {
    private val sharedName = "rmDatabase"
    private val sharedCharacterFavoriteId = "characterId"
    private val storage = context.getSharedPreferences(sharedName, 0)

    fun saveFavoriteCharacter(characterId: String) {
        storage.edit().putString(sharedCharacterFavoriteId, characterId).apply()
    }

    fun getFavoriteCharacter(): String? {
        return storage.getString(sharedCharacterFavoriteId, null)
    }
}