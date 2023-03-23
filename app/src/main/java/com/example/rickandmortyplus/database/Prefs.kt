package com.example.rickandmortyplus.database

import android.content.Context
import android.util.Log
import com.example.rickandmortyplus.EMPTY_STR
import com.example.rickandmortyplus.ONE_NUMBER
import com.example.rickandmortyplus.ONE_STR
import com.example.rickandmortyplus.RM_DATABASE

class Prefs(context: Context) {
    private val sharedName = RM_DATABASE
    private val storage = context.getSharedPreferences(sharedName, 0)

    private var characterIdStr = EMPTY_STR
    fun saveFavoriteCharacter(characterId: String) {
        Log.d(Companion.TAG_PREFS, "Saving...")
        characterIdStr = validateOne(characterId = characterId)
        storage.edit().putString(characterIdStr, characterIdStr).apply()
    }

    fun getFavoriteCharacter(characterId: String): String? {
        Log.d(Companion.TAG_PREFS, "Getting...")
        characterIdStr = validateOne(characterId = characterId)
        return storage.getString(characterIdStr, null)
    }

    fun removeFavoriteCharacter(characterId: String) {
        Log.d(Companion.TAG_PREFS, "Removing...")
        characterIdStr = validateOne(characterId = characterId)
        storage.edit().remove(characterIdStr).apply()
    }

    private fun validateOne(characterId: String): String {
        return if (characterId === ONE_NUMBER) {
            ONE_STR
        } else {
            characterId
        }
    }

    companion object {
        private const val TAG_PREFS = "prefsS"
    }
}