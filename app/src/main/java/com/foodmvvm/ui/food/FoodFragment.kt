package com.foodmvvm.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.foodmvvm.R
import com.foodmvvm.common.BaseFragment
import com.foodmvvm.common.setAsVerticalList
import com.foodmvvm.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment : BaseFragment(), FoodView {
    companion object {
        fun create(): FoodFragment {
            return FoodFragment()
        }
    }

    override val foodAdapter: FoodAdapter
        get() {
            return (recyclerview_foods.adapter as? FoodAdapter) ?: createFoodAdapter().apply {
                recyclerview_foods.adapter = this
            }
        }

    val viewModel by lazy {
        val factory = InjectorUtils.provideFoodViewModelFactory(ctx)
        ViewModelProviders.of(this, factory).get(FoodViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onStart() {
        super.onStart()
        recyclerview_foods.setAsVerticalList(ctx)
        viewModel.foods.observe(this, Observer { foods ->
            foodAdapter.update(foods)
        })
    }


    private fun createFoodAdapter(): FoodAdapter {
        return FoodAdapter(layoutInflater)
    }
}

interface FoodView {
    val foodAdapter: FoodAdapter
}