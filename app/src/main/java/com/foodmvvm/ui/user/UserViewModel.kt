package com.foodmvvm.ui.user

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foodmvvm.data.User
import com.foodmvvm.utils.AppPrefs
import com.pixplicity.easyprefs.library.Prefs

class UserViewModel(val ctx: Context) : ViewModel(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val emptyUser = User()

    private val prefs by lazy {
        AppPrefs.getInstance(ctx)
    }

    val user = MutableLiveData<User>()

    init {
        user.value = prefs.user
        Prefs.getPreferences().registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            prefs.KEY_USER -> {
                user.value = prefs.user ?: emptyUser
            }
        }
    }

    fun update(user: User) {
        prefs.user = user
    }

}

class UserViewModelFactory(val ctx: Context) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(ctx) as T
    }
}