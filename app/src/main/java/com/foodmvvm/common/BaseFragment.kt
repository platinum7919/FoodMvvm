package com.foodmvvm.common

import android.content.Context
import androidx.fragment.app.Fragment
import com.foodmvvm.App
import com.foodmvvm.appCtx

open class BaseFragment : Fragment() {
    val ctx: Context
        get() {
            return activity?.applicationContext ?: appCtx
        }
}