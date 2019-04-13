package com.foodmvvm.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.foodmvvm.R
import com.foodmvvm.common.BaseActivity
import com.foodmvvm.data.User
import com.foodmvvm.ui.toGenderText
import com.foodmvvm.ui.user.UserViewModel
import com.foodmvvm.utils.InjectorUtils
import com.foodmvvm.utils.nullIfEmptyOrBlank
import kotlinx.android.synthetic.main.activity_user_detail.*

/**
 * A simple detail [Activity] that shows a [User] object (read-only)
 */
class UserDetailActivity : BaseActivity(), UserDetailView {

    private val userViewModel by lazy {
        val factory = InjectorUtils.provideUserViewModelFactory(this)
        ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
    }

    private val userDetailViewModel by lazy {
        val factory = InjectorUtils.provideUserDetailViewModelFactory(this)
        ViewModelProviders.of(this, factory).get(UserDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        userViewModel.user.observe(this, Observer {
            update(it)
        })
    }


    override fun onBackPressed() {
        userDetailViewModel.onBackPressed(this)
    }


    /**
     * update UI given an [User] object
     */
    fun update(user: User) {
        supportActionBar?.title = user.name
        textview_user_gender.text = getString(
            R.string.label_user_detail_format,
            getString(R.string.label_gender),
            user.gender?.toGenderText(this) ?: getString(R.string.na)
        )

        textview_user_comment.text = getString(
            R.string.label_user_detail_format,
            getString(R.string.label_comment),
            "\n" + (user.comment?.nullIfEmptyOrBlank() ?: getString(R.string.na))
        )

    }

}