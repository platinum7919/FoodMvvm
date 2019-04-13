package com.foodmvvm.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDetailViewModel(val ctx: Context) : ViewModel() {

    fun onBackPressed(view: UserDetailView) {
        view.finish()
    }
}


class UserDetailViewModelFactory(val ctx: Context) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailViewModel(ctx) as T
    }
}


interface UserDetailView {
    fun finish()
}