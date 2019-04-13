package com.foodmvvm.data

import com.google.gson.annotations.SerializedName

data class Food(@SerializedName("foodName") val name: String)

class FoodList : ArrayList<Food>()