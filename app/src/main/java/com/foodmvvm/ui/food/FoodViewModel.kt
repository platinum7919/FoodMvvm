package com.foodmvvm.ui.food

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foodmvvm.data.Food
import com.foodmvvm.data.FoodList
import com.foodmvvm.utils.Assets
import com.foodmvvm.utils.Json

class FoodViewModel(val ctx: Context) : ViewModel() {

    val foods = MutableLiveData<List<Food>>()

    init {
        foods.value = Json.toObject<FoodList>(Assets.readString(ctx, "food/burger_king.json"))?.toMutableList()
            ?: mutableListOf()
    }

}

class FoodViewModelFactory(val ctx: Context) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FoodViewModel(ctx) as T
    }
}