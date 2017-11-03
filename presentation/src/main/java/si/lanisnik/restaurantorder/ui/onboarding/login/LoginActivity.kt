package si.lanisnik.restaurantorder.ui.onboarding.login

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import si.lanisnik.restaurantorder.ui.base.extensions.hideKeyboard
import si.lanisnik.restaurantorder.ui.base.extensions.input
import si.lanisnik.restaurantorder.ui.base.extensions.snackbar
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

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
        loginButton.setOnClickListener {
            onLoginClicked()
        }
    }

    private fun onLoginClicked() {
        val email = loginEmailEditText.input()
        val password = loginPasswordEditText.input()
        if (viewModel.isInputValid(email, password)) {
            viewModel.performLogin(email, password)
        } else {
            loginButton.snackbar(R.string.login_invalid_input)
        }
        hideKeyboard()
    }

}