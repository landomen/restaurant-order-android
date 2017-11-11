package si.lanisnik.restaurantorder.ui.dashboard

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.hide
import si.lanisnik.restaurantorder.ui.base.extensions.show
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.dashboard.adapter.DashboardButtonsAdapter
import si.lanisnik.restaurantorder.ui.dashboard.model.DashboardButtonModel
import si.lanisnik.restaurantorder.ui.dashboard.navigator.DashboardNavigator
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject lateinit var navigator: DashboardNavigator
    @Inject lateinit var viewModelFactory: DashboardViewModelFactory
    private lateinit var viewModel: DashboardViewModel

    override fun getContentView(): Int = R.layout.activity_dashboard

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.app_name)
    }

    override fun initUi() {
        dashboardShoppingCartView.setOnClickListener {
            // TODO Open shopping cart preview
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        viewModel.getFullDashboardObservable().observe(this, Observer {
            handleDataState(it!!)
        })
    }

    private fun handleDataState(state: Resource<Boolean>) {
        when (state.status) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> displayButtons(state.data!!)
            ResourceState.ERROR -> displayButtons(false)
        }
        dashboardShoppingCartView.changeVisibility(state.status != ResourceState.LOADING)
    }

    private fun displayButtons(fullDashboard: Boolean) {
        dashboardLoadingStateView.hide()
        dashboardGridView.adapter = DashboardButtonsAdapter.create(fullDashboard) {
            onButtonClicked(it)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        dashboardLoadingStateView.state = state
        dashboardLoadingStateView.show()
    }

    private fun onButtonClicked(type: DashboardButtonModel) {
        when (type) {
            is DashboardButtonModel.Order -> navigator.navigateToCategories(this)
            is DashboardButtonModel.History -> navigator.navigateToHistory(this)
            is DashboardButtonModel.Profile -> navigator.navigateToProfile(this)
            is DashboardButtonModel.Login -> navigator.navigateToLogin(this)
            is DashboardButtonModel.About -> navigator.navigateToAbout(this)
        }
    }
}
