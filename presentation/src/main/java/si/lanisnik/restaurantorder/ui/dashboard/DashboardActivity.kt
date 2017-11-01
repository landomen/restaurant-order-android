package si.lanisnik.restaurantorder.ui.dashboard

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.dashboard.navigator.DashboardNavigator
import si.lanisnik.restaurantorder.ui.onboarding.navigator.OnboardingNavigator
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject lateinit var navigator: DashboardNavigator
    @Inject lateinit var onboardingNavigator: OnboardingNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getContentView(): Int = R.layout.activity_dashboard

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.app_name)
    }

    override fun initUi() {
        floatingActionButton.setOnClickListener {
            navigator.navigateToCategories(this)
        }
        dashboardLoginButton.setOnClickListener {
            onboardingNavigator.navigateToLogin(this)
        }
    }

}
