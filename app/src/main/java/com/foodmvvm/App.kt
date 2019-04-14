package com.foodmvvm

import android.app.Application
import android.content.Context
import timber.log.Timber


lateinit var appCtx: Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appCtx = this.applicationContext
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}