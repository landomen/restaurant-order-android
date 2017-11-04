package si.lanisnik.restaurantorder.ui.onboarding.password

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.indeterminateProgressDialog
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class ResetPasswordActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ResetPasswordViewModelFactory
    private lateinit var viewModel: ResetPasswordViewModel
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel(viewModelFactory)
        setupObservers()
    }

    override fun getContentView(): Int = R.layout.activity_reset_password

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.reset_password)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        resetPasswordEmailEditText.onDoneAction { onResetClicked() }
        resetPasswordButton.setOnClickListener { onResetClicked() }
    }

    private fun setupObservers() {
        viewModel.getValidationObservable().observe(this, Observer {
            handleValidationError(it!!)
        })
        viewModel.getActionObservable().observe(this, Observer {
            handleState(it!!)
        })
    }

    private fun onResetClicked() {
        viewModel.resetPassword(resetPasswordEmailEditText.input())
        hideKeyboard()
    }

    private fun handleValidationError(error: InputError) {
        resetPasswordButton.snackbar(error.message)
    }

    private fun handleState(state: SimpleResource) {
        when (state.status) {
            ResourceState.LOADING -> showLoadingState()
            ResourceState.SUCCESS -> showSuccessState()
            ResourceState.ERROR -> showErrorState(state.errorMessage!!)
        }
    }

    private fun showLoadingState() {
        loadingDialog = indeterminateProgressDialog(R.string.general_loading) {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        loadingDialog.show()
    }

    private fun showSuccessState() {
        loadingDialog.dismiss()
        MaterialDialog.Builder(this)
                .content(R.string.reset_password_success)
                .canceledOnTouchOutside(false)
                .cancelable(false)
                .positiveText(R.string.general_ok)
                .onPositive { dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
                .show()
    }

    private fun showErrorState(errorMessage: Int) {
        loadingDialog.dismiss()
        resetPasswordButton.snackbar(errorMessage)
    }
}