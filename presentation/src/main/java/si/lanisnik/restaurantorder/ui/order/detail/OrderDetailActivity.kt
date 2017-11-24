package si.lanisnik.restaurantorder.ui.order.detail

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.order.detail.adapter.OrderStatusRecyclerAdapter
import si.lanisnik.restaurantorder.ui.order.model.OrderModel
import si.lanisnik.restaurantorder.ui.order.shoppingcart.adapter.ShoppingCartRecyclerAdapter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderDetailActivity : BaseActivity(), LoadingStateView.RetryListener {

    companion object {
        const val EXTRA_ORDER_ID = "si.lanisnik.restaurantorder.ui.order.detail.OrderId"
    }

    @Inject lateinit var viewModelFactory: OrderDetailViewModelFactory
    @Inject lateinit var menuItemsAdapter: ShoppingCartRecyclerAdapter
    @Inject lateinit var statusAdapter: OrderStatusRecyclerAdapter
    private lateinit var viewModel: OrderDetailViewModel

    override fun getContentView(): Int = R.layout.activity_order_detail

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.order_detail_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        orderDetailLoadingStateView.retryListener = this
        setupMenuItemsRecyclerView()
        setupStatusRecyclerView()
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize(intent.getIntExtra(EXTRA_ORDER_ID, 0))
    }

    override fun setupObservers() {
        viewModel.getOrderObservable().observe(this, Observer {
            handleDataState(it!!)
        })
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    private fun setupMenuItemsRecyclerView() {
        menuItemsAdapter.editActionEnabled = false
        orderDetailItemsRecyclerView.adapter = menuItemsAdapter
        orderDetailItemsRecyclerView.enableItemDividers()
    }

    private fun setupStatusRecyclerView() {
        orderDetailStatusRecyclerView.adapter = statusAdapter
        orderDetailStatusRecyclerView.enableItemDividers()
    }

    private fun handleDataState(resource: Resource<OrderModel>) {
        when (resource.status) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> renderDetails(resource.data!!)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        orderDetailLoadingStateView.state = state
        orderDetailLoadingStateView.show()
        orderDetailScrollView.hide()
    }

    private fun renderDetails(order: OrderModel) {
        orderDetailCreatedText.text = SimpleDateFormat("dd.MM.yyyy 'ob' HH:mm", Locale.GERMAN).format(Date(order.createdAt))
        orderDetailAddressText.text = order.address.getAddress()
        orderDetailTotalText.text = "${order.total}€"
        orderDetailCommentText.text = order.customerComment ?: getString(R.string.menu_item_no_comment)
        menuItemsAdapter.updateItems(order.menuItems)
        statusAdapter.items = order.orderChanges

        orderDetailScrollView.show()
        orderDetailLoadingStateView.hide()
    }

}