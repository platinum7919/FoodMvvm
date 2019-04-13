package com.foodmvvm.utils

import android.content.Context
import android.content.ContextWrapper
import com.foodmvvm.data.User
import com.pixplicity.easyprefs.library.Prefs

object AppPrefs {

    var inited = false
    @Synchronized
    fun setup(ctx: Context) {
        if (inited) {
            return
        }

        Prefs.Builder()
            .setContext(ctx)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(ctx.packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        inited = true
    }

    val KEY_USER = "KEY_USER1"

    var user: User?
        get() {
            return Prefs.getString(KEY_USER, null)?.let {
                Json.toObject<User>(it)
            }
        }
        set(value) {
            Prefs.putString(KEY_USER, if (value == null) null else Json.toJson(value))
        }


}