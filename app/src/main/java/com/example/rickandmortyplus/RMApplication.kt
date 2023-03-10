package com.example.rickandmortyplus

import android.annotation.SuppressLint
import android.app.Application
import com.example.rickandmortyplus.database.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RMApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}