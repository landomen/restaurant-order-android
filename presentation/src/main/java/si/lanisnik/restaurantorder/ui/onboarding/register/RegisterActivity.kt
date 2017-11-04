package si.lanisnik.restaurantorder.ui.onboarding.register

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.indeterminateProgressDialog
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import si.lanisnik.restaurantorder.ui.onboarding.navigator.OnboardingNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class RegisterActivity : BaseActivity() {

    companion object {
        const val EXTRA_STARTED_FROM_LOGIN = "ui.onboarding.register.FromLogin"
    }

    @Inject lateinit var onboardingNavigator: OnboardingNavigator
    @Inject lateinit var viewModelFactory: RegisterViewModelFactory
    private lateinit var viewModel: RegisterViewModel
    private lateinit var loadingDialog: Dialog
    private var startedFromLogin = false

    override fun getContentView(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startedFromLogin = intent.getBooleanExtra(EXTRA_STARTED_FROM_LOGIN, false)
        viewModel = createViewModel(viewModelFactory)
        setupObservers()
    }

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.register)
        toolbar.setNavigationOnClickListener { finish() }

    }

    override fun initUi() {
        registerButton.setOnClickListener { onRegisterClicked() }
        registerPasswordConfirmationEditText.onDoneAction { onRegisterClicked() }
        registerLoginText.setOnClickListener {
            if (startedFromLogin)
                finish()
            else
                onboardingNavigator.navigateToLogin(this)
        }
    }

    private fun setupObservers() {
        viewModel.getValidationObservable().observe(this, Observer {
            handleValidationError(it!!)
        })
        viewModel.getRegistrationObservable().observe(this, Observer {
            handleState(it!!)
        })
    }

    private fun onRegisterClicked() {
        viewModel.register(
                registerFirstNameEditText.input(),
                registerLastNameEditText.input(),
                registerAddressEditText.input(),
                registerEmailEditText.input(),
                registerPhoneNumberEditText.input(),
                registerPasswordEditText.input(),
                registerPasswordConfirmationEditText.input()
        )
        hideKeyboard()
    }

    private fun handleValidationError(error: InputError) {
        registerButton.snackbar(error.message)
    }

    private fun handleState(state: SimpleResource) {
        when (state.status) {
            ResourceState.LOADING -> showLoadingState()
            ResourceState.SUCCESS -> showSuccessState()
            ResourceState.ERROR -> showErrorState(state.errorMessage!!)
        }
    }

    private fun showLoadingState() {
        loadingDialog = indeterminateProgressDialog(R.string.register_in_progress) {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
        loadingDialog.show()
    }

    private fun showSuccessState() {
        loadingDialog.dismiss()
        onboardingNavigator.navigateToDashboard(this)
    }

    private fun showErrorState(errorMessage: Int) {
        loadingDialog.dismiss()
        registerButton.snackbar(errorMessage)
    }
}