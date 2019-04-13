package com.foodmvvm.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModel(val ctx: Context) : ViewModel() {

    fun onUserTabSelected(view: HomeView) {
        view.showUser()
    }

    fun onFoodTabSelected(view: HomeView) {
        view.showFood()
    }

    fun onActionUserDetail(view: HomeView) {
        view.showUserDetail()
    }
}


class HomeViewModelFactory(val ctx: Context) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(ctx) as T
    }
}


interface HomeView {
    fun showUserDetail()
    fun showUser()
    fun showFood()
}