package si.lanisnik.restaurantorder.ui.onboarding.login

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
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
class LoginActivity : BaseActivity() {

    @Inject lateinit var onboardingNavigator: OnboardingNavigator
    @Inject lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var viewModel: LoginViewModel

    override fun getContentView(): Int = R.layout.activity_login

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.login)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        loginRegisterText.setOnClickListener {
            onboardingNavigator.navigateToRegister(this, true)
        }
        loginResetPasswordText.setOnClickListener {
            onboardingNavigator.navigateToResetPassword(this)
        }
        loginPasswordEditText.onDoneAction { onLoginClicked() }
        loginButton.setOnClickListener { onLoginClicked() }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {
        viewModel.getValidationObservable().observe(this, Observer {
            handleValidationError(it!!)
        })
        viewModel.getLoginObservable().observe(this, Observer {
            handleState(it!!)
        })
    }

    private fun onLoginClicked() {
        viewModel.login(loginEmailEditText.input(), loginPasswordEditText.input())
        hideKeyboard()
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
        onboardingNavigator.navigateToDashboard(this)
    }

    private fun showErrorState(errorMessage: Int) {
        hideLoadingDialog()
        loginButton.snackbar(errorMessage)
    }

    private fun handleValidationError(error: InputError) {
        loginButton.snackbar(error.message)
    }

}