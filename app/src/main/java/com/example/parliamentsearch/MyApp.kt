package com.example.parliamentsearch

import android.app.Application
import android.content.Context
//This code defines an Android Application class MyApp which extends Application.
//This code is used to provide a single, global context object for the entire application.
class MyApp : Application() {
    companion object {
        lateinit var appContext: Context
        private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
