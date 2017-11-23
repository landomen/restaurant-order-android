package si.lanisnik.restaurantorder.ui.order.history

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_history_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.order.history.adapter.OrderHistoryRecyclerAdapter
import si.lanisnik.restaurantorder.ui.order.model.OrderHistoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: OrderHistoryListViewModelFactory
    @Inject lateinit var adapter: OrderHistoryRecyclerAdapter
    private lateinit var viewModel: OrderHistoryListViewModel

    override fun getContentView(): Int = R.layout.activity_order_history_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.history_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        setupRecyclerView()
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        viewModel.getOrdersObservable().observe(this, Observer {
            handleDataState(it!!)
        })
    }

    private fun setupRecyclerView() {
        adapter.listener = {
            viewModel.onOrderSelected(it)
        }
        ordersHistoryRecyclerView.adapter = adapter
        ordersHistoryRecyclerView.enableItemDividers()
    }

    private fun handleDataState(resource: Resource<List<OrderHistoryModel>>) {
        when (resource.status) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showOrders(resource.data!!)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        ordersHistoryLoadingStateView.state = state
        ordersHistoryLoadingStateView.show()
        ordersHistoryRecyclerView.hide()
    }

    private fun showOrders(orders: List<OrderHistoryModel>) {
        if (orders.isNotNullAndEmpty()) {
            adapter.orders = orders
            ordersHistoryRecyclerView.show()
            ordersHistoryLoadingStateView.hide()
        } else {
            showLoadingState(LoadingStateView.State.EMPTY)
        }
    }
}