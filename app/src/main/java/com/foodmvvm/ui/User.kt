package com.foodmvvm.ui

import android.content.Context
import com.foodmvvm.R
import com.foodmvvm.data.User


fun Int.toGenderText(ctx: Context): String? {
    return when (this) {
        User.MALE -> R.string.male
        User.FEMALE -> R.string.female
        else -> null
    }?.let {
        ctx.resources.getString(it)
    }
}
