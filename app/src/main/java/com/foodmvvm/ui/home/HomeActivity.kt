package com.foodmvvm.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.foodmvvm.R
import com.foodmvvm.common.BaseActivity
import com.foodmvvm.ui.detail.UserDetailActivity
import com.foodmvvm.ui.food.FoodFragment
import com.foodmvvm.ui.user.UserFragment
import com.foodmvvm.utils.InjectorUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

typealias BottomTabListener = BottomNavigationView.OnNavigationItemSelectedListener

class HomeActivity : BaseActivity(), HomeView {

    private val viewModel by lazy {
        val factory = InjectorUtils.provideHomeViewModelFactory(this)
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }


    private val onTabItemSelected = BottomTabListener { item ->
        when (item.itemId) {
            R.id.tab_item_food -> {
                viewModel.onFoodTabSelected(this)
            }
            R.id.tab_item_user -> {
                viewModel.onUserTabSelected(this)
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.setTitle(R.string.title_home)


        bottom_tab.setOnNavigationItemSelectedListener(onTabItemSelected)
        bottom_tab.selectedItemId = R.id.tab_item_user

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_items_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.item_user_detail -> {
                viewModel.onActionUserDetail(this)
                true
            }
            else -> false
        }
    }

    override fun showUser() {
        putFragmentInLayout(R.id.layout_fragment_container, UserFragment.create())
    }

    override fun showFood() {
        putFragmentInLayout(R.id.layout_fragment_container, FoodFragment.create())
    }

    override fun showUserDetail() {
        Intent(this, UserDetailActivity::class.java).apply {
            startActivity(this)
        }
    }
}

