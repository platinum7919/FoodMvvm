package com.foodmvvm.utils

import android.content.Context
import com.foodmvvm.ui.detail.UserDetailViewModelFactory
import com.foodmvvm.ui.food.FoodViewModelFactory
import com.foodmvvm.ui.home.HomeViewModelFactory
import com.foodmvvm.ui.user.UserViewModelFactory

/**
 * Might later want to use a [Repository] class to handle where the data is coming from
 *
 * inject dao object into [Repository] also
 */
object InjectorUtils {

    fun provideHomeViewModelFactory(ctx: Context): HomeViewModelFactory {
        return HomeViewModelFactory(ctx)
    }

    fun provideFoodViewModelFactory(ctx: Context): FoodViewModelFactory {
        return FoodViewModelFactory(ctx)
    }

    fun provideUserViewModelFactory(ctx: Context): UserViewModelFactory {
        return UserViewModelFactory(ctx)
    }

    fun provideUserDetailViewModelFactory(ctx: Context): UserDetailViewModelFactory {
        return UserDetailViewModelFactory(ctx)
    }


}

class UserDetailViewModelFactory