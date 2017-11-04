package si.lanisnik.restaurantorder.ui.onboarding.register

import android.arch.lifecycle.Observer
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
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
        registerLoginText.setOnClickListener {
            if (startedFromLogin)
                finish()
            else
                onboardingNavigator.navigateToLogin(this)
        }
    }

    private fun setupObservers() {
        viewModel.getObservable()
                .observe(this, Observer {

                })
    }
}