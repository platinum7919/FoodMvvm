package com.foodmvvm.ui.food

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.foodmvvm.R
import com.foodmvvm.common.ListRecyclerViewAdapter
import com.foodmvvm.common.RecyclerViewItem
import com.foodmvvm.common.RecyclerViewViewHolder
import com.foodmvvm.data.Food

class FoodAdapter(layoutInflater: LayoutInflater) : ListRecyclerViewAdapter<FoodItem>(layoutInflater) {

    fun update(foods: List<Food>) {
        update(foods.map { FoodItem(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder<FoodItem> {
        return FoodViewHolder(layoutInflater, parent)
    }

    class FoodViewHolder(layoutInflator: LayoutInflater, parent: ViewGroup) : RecyclerViewViewHolder<FoodItem>(
        layoutInflator.run {
            inflate(R.layout.layout_list_item_food, parent, false)
        }
    ) {

        val nameText by lazy {
            itemView.findViewById<TextView>(R.id.textview_food_name)
        }

        override fun onBindViewHolder(position: Int, total: Int, item: FoodItem) {
            nameText.text = item.food.name
        }
    }
}

class FoodItem(val food: Food) : RecyclerViewItem {
    override val id: String
        get() = food.name
    override val viewType: Int
        get() = 0

}