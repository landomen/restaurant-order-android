package si.lanisnik.restaurantorder.ui.onboarding.login

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import si.lanisnik.restaurantorder.ui.onboarding.navigator.OnboardingNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginActivity : BaseActivity() {

    @Inject lateinit var onboardingNavigator: OnboardingNavigator

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
    }

}