package si.lanisnik.restaurantorder.ui.order.success

import kotlinx.android.synthetic.main.activity_order_success.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.onboarding.navigator.OnboardingNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 19/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderSuccessActivity : BaseActivity() {

    @Inject lateinit var navigator: OnboardingNavigator
    @Inject lateinit var viewModelFactory: OrderSuccessViewModelFactory
    private lateinit var viewModeL: OrderSuccessViewModel

    override fun getContentView(): Int = R.layout.activity_order_success

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.send_order_success_toolbar_title)
    }

    override fun initUi() {
        successCloseButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initViewModel() {
        viewModeL = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {

    }

    override fun onBackPressed() {
        navigator.navigateToDashboard(this)
    }
}