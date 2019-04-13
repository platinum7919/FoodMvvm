package com.foodmvvm.data

import com.google.gson.annotations.SerializedName

data class User(var name: String? = null, var gender: Int? = null, var comment: String? = null) {
    companion object {
        val MALE = 1
        val FEMALE = 2
    }
}
