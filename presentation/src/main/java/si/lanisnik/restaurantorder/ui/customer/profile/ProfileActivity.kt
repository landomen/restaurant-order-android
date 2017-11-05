package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import si.lanisnik.restaurantorder.ui.base.extensions.hide
import si.lanisnik.restaurantorder.ui.base.extensions.show
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import si.lanisnik.restaurantorder.ui.customer.navigator.CustomerNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileActivity : BaseActivity(), LoadingStateView.RetryListener {

    @Inject lateinit var navigator: CustomerNavigator
    @Inject lateinit var viewModelFactory: ProfileViewModelFactory
    private lateinit var viewModel: ProfileViewModel

    override fun getContentView(): Int = R.layout.activity_profile

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        setTitle(R.string.dashboard_profile)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        profileLoadingStateView.retryListener = this
        profilePasswordChangeButton.setOnClickListener {
            navigator.navigateToChangePassword(this)
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        viewModel.getDetailsObservable().observe(this, Observer {
            handleDataState(it!!.status, it.data)
        })
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    private fun handleDataState(state: ResourceState, data: CustomerModel?) {
        when (state) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showProfileInfo(data!!)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showProfileInfo(customer: CustomerModel) {
        showBasicInfo(customer)
        profileLoadingStateView.hide()
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        profileLoadingStateView.state = state
        profileLoadingStateView.show()
    }

    private fun showBasicInfo(customer: CustomerModel) {
        profileFirstNameEditText.setText(customer.firstName)
        profileLastNameEditText.setText(customer.lastName)
        profileEmailEditText.setText(customer.email)
        profilePhoneNumberEditText.setText(customer.phoneNumber)
    }

}