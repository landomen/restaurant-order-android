package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.Observer
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.dialogs.DialogHelper
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import si.lanisnik.restaurantorder.ui.customer.navigator.CustomerNavigator
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import si.lanisnik.restaurantorder.ui.onboarding.navigator.OnboardingNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileActivity : BaseActivity(), LoadingStateView.RetryListener {

    @Inject lateinit var navigator: CustomerNavigator
    @Inject lateinit var onboardingNavigator: OnboardingNavigator
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
        profileInfoCancelButton.setOnClickListener {
            viewModel.restoreDetails()
            hideKeyboard()
        }
        profileInfoSaveButton.setOnClickListener {
            onSaveProfileInfo()
        }
        profileAddressOpenButton.setOnClickListener {
            navigator.navigateToAddresses(this)
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
        viewModel.getUpdateObservable().observe(this, Observer {
            handleUpdateState(it!!)
        })
        viewModel.getValidationObservable().observe(this, Observer {
            handleUpdateValidationError(it!!)
        })
        viewModel.getLogoutObservable().observe(this, Observer {
            onboardingNavigator.navigateToDashboard(this)
        })
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_profile_logout)
            viewModel.logoutClicked()
        return super.onOptionsItemSelected(item)
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

    private fun handleUpdateValidationError(error: InputError) {
        profileInfoSaveButton.snackbar(error.message)
    }

    private fun handleUpdateState(state: SimpleResource) {
        when (state.status) {
            ResourceState.LOADING -> showUpdateLoadingState()
            ResourceState.SUCCESS -> showUpdateSuccessState()
            ResourceState.ERROR -> showUpdateErrorState(state.errorMessage!!)
        }
    }

    private fun showUpdateLoadingState() {
        showLoadingDialog()
    }

    private fun showUpdateSuccessState() {
        hideLoadingDialog()
        DialogHelper.showSuccessDialog(this, R.string.profile_update_success, null)
    }

    private fun showUpdateErrorState(errorMessage: Int) {
        hideLoadingDialog()
        profileInfoSaveButton.snackbar(errorMessage)
    }

    private fun onSaveProfileInfo() {
        viewModel.updateProfile(profileFirstNameEditText.input(),
                profileLastNameEditText.input(),
                profileEmailEditText.input(),
                profilePhoneNumberEditText.input())
        hideKeyboard()
    }

}