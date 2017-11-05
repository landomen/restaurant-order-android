package si.lanisnik.restaurantorder.ui.customer.password

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.dialogs.DialogHelper
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ChangePasswordActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ChangePasswordViewModelFactory
    private lateinit var viewModel: ChangePasswordViewModel

    override fun getContentView(): Int = R.layout.activity_change_password

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.profile_password_change)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        changePasswordConfirmationEditText.onDoneAction {
            onConfirmChange()
        }
        changePasswordButton.setOnClickListener {
            onConfirmChange()
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {
        viewModel.getValidationObservable().observe(this, Observer {
            handleValidationError(it!!)
        })
        viewModel.getActionObservable().observe(this, Observer {
            handleState(it!!)
        })
    }

    private fun onConfirmChange() {
        viewModel.changePassword(changePasswordOldEditText.input(),
                changePasswordNewEditText.input(),
                changePasswordConfirmationEditText.input())
        hideKeyboard()
    }

    private fun handleValidationError(error: InputError) {
        changePasswordButton.snackbar(error.message)
    }

    private fun handleState(state: SimpleResource) {
        when (state.status) {
            ResourceState.LOADING -> showLoadingState()
            ResourceState.SUCCESS -> showSuccessState()
            ResourceState.ERROR -> showErrorState(state.errorMessage!!)
        }
    }

    private fun showLoadingState() {
        showLoadingDialog()
    }

    private fun showSuccessState() {
        hideLoadingDialog()
        DialogHelper.showSuccessDialog(this, R.string.profile_change_password_success) {
            finish()
        }
    }

    private fun showErrorState(errorMessage: Int) {
        hideLoadingDialog()
        changePasswordButton.snackbar(errorMessage)
    }
}