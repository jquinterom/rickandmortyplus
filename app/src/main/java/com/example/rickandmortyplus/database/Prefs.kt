package com.example.rickandmortyplus.database

import android.content.Context
import android.util.Log

class Prefs(context: Context) {
    private val sharedName = "rmDatabase"
    private val storage = context.getSharedPreferences(sharedName, 0)

    fun saveFavoriteCharacter(characterId: String) {
        Log.d("prefsS", "Saving...")
        storage.edit().putString(characterId, characterId).apply()
    }

    fun getFavoriteCharacter(characterId: String): String? {
        Log.d("prefsG", "Getting...")
        return storage.getString(characterId, null)
    }

    fun removeFavoriteCharacter(characterId: String){
        Log.d("prefsR", "Removing...")
        storage.edit().remove(characterId).apply()
    }
}