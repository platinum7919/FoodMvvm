package com.foodmvvm

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.foodmvvm.utils.AppPrefs
import com.pixplicity.easyprefs.library.Prefs
import timber.log.Timber


lateinit var appCtx: Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appCtx = this.applicationContext
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Initialize the Prefs class
        AppPrefs.setup(this)
    }
}