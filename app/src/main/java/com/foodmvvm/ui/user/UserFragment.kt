package com.foodmvvm.ui.user

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.foodmvvm.R
import com.foodmvvm.common.BaseFragment
import com.foodmvvm.common.Dialogs
import com.foodmvvm.common.dismissSafely
import com.foodmvvm.data.User
import com.foodmvvm.ui.toGenderText
import com.foodmvvm.utils.InjectorUtils
import com.foodmvvm.utils.nullIfEmptyOrBlank
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : BaseFragment() {

    companion object {
        fun create(): UserFragment {
            return UserFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    var dialog: Dialog? = null


    val viewModel by lazy {
        val factory = InjectorUtils.provideUserViewModelFactory(ctx)
        ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
    }


    /**
     * Setup the initial view
     * - Make sure it saves when [android.widget.EditText] is changed
     * - Make sure that when select button is pressed, we show selection dialog
     */
    fun setup() {
        button_select_gender.setOnClickListener {
            showGenderSelectionOptions()
        }


        val onFocusChangedListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                save()
            }
        }

        text_input_edit_text_name.onFocusChangeListener = onFocusChangedListener
        text_input_edit_text_comment.onFocusChangeListener = onFocusChangedListener

        viewModel.user.observe(this, Observer {
            update(it)
        })
    }

    /**
     * Show a [Dialog] that allow user to select [User.gender] values
     */
    private fun showGenderSelectionOptions() {
        activity?.let { parent ->
            dialog =
                Dialogs.showAlertDialogOptions(
                    activity = parent,
                    title = getString(R.string.label_gender),
                    options = listOf(
                        User.MALE to getString(R.string.male),
                        User.FEMALE to getString(R.string.female)
                    ),
                    onSelected = {
                        selectedGender = it
                        save()
                    })
        }
    }

    /**
     * Update the view with values in [user]
     */
    @Synchronized
    private fun update(user: User?) {
        text_input_edit_text_name.setText(user?.name ?: "")
        text_input_edit_text_comment.setText(user?.comment ?: "")
        selectedGender = user?.gender
    }


    /**
     * Set the selected [User.gender] on the view level only
     */
    private var selectedGender: Int?
        get() {
            return button_select_gender.getTag(R.id.value) as? Int
        }
        @MainThread
        set(value) {
            button_select_gender.setTag(R.id.value, value)
            button_select_gender.text = value?.toGenderText(ctx) ?: getString(R.string.select)
        }


    /**
     * Save the input as an [User] object into [UserViewModel]
     */
    private fun save() {
        viewModel.update(User().apply {
            name = text_input_edit_text_name.text?.toString()?.nullIfEmptyOrBlank()
            gender = selectedGender
            comment = text_input_edit_text_comment.text?.toString()?.nullIfEmptyOrBlank()
        })
    }


    /**
     * Dismiss any dialog. Also [save] current input
     */
    override fun onPause() {
        super.onPause()
        save()
        dialog?.dismissSafely()
    }

    /**
     * do start initialization and update initial UI
     */
    override fun onStart() {
        super.onStart()
        setup()
    }


}

