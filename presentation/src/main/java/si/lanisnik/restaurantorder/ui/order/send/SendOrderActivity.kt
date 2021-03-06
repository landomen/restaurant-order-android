package si.lanisnik.restaurantorder.ui.order.send

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_send_order.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.address.adapter.AddressRecyclerAdapter
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.order.navigator.OrderNavigator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class SendOrderActivity : BaseActivity(), LoadingStateView.RetryListener, AddressRecyclerAdapter.AddressListener {

    @Inject lateinit var viewModelFactory: SendOrderViewModelFactory
    @Inject lateinit var addressAdapter: AddressRecyclerAdapter
    @Inject lateinit var navigator: OrderNavigator
    private lateinit var viewModel: SendOrderViewModel

    override fun getContentView(): Int = R.layout.activity_send_order

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.send_order)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        setupRecyclerView()
        sendOrderStateView.retryListener = this
        sendOrderButton.setOnClickListener {
            viewModel.createOrder(sendOrderCommentEditText.input())
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        setupAddressesObserver()
        setupCreateOrderObserver()
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    override fun onAddressSelected(addressId: Int) {
        viewModel.onAddressSelected(addressId)
    }

    override fun onAddressDelete(addressId: Int) {
        // Not supported
    }

    private fun setupRecyclerView() {
        addressAdapter.editingEnabled = false
        addressAdapter.listener = this
        sendOrderAddressRecyclerView.adapter = addressAdapter
        sendOrderAddressRecyclerView.enableItemDividers()
    }

    private fun setupAddressesObserver() {
        viewModel.getAddressesObservable().observe(this, Observer { resource ->
            when (resource!!.status) {
                ResourceState.LOADING -> handleLoadingState()
                ResourceState.SUCCESS -> showAddresses(resource.data!!)
                ResourceState.ERROR -> handleErrorState()
            }
        })
    }

    private fun setupCreateOrderObserver() {
        viewModel.getCreateObservable().observe(this, Observer { resource ->
            when (resource!!.status) {
                ResourceState.LOADING -> handleLoadingState()
                ResourceState.SUCCESS -> onOrderCreated()
                ResourceState.ERROR -> {
                    sendOrderStateView.hide()
                    sendOrderButton.show()
                    sendOrderStateView.snackbar(resource.errorMessage!!)
                }
            }
        })
    }

    private fun handleLoadingState() {
        sendOrderStateView.state = LoadingStateView.State.LOADING
        sendOrderButton.hide()
        sendOrderStateView.show()
    }

    private fun handleErrorState() {
        sendOrderStateView.state = LoadingStateView.State.ERROR
        sendOrderButton.hide()
        sendOrderStateView.show()
    }

    private fun showAddresses(addresses: List<AddressModel>) {
        addressAdapter.addresses = addresses.toMutableList()
        sendOrderStateView.hide()

        sendOrderButton.changeVisibility(addresses.isNotEmpty())
        sendOrderAddressRecyclerView.changeVisibility(addresses.isNotEmpty())
        sendOrderAddressEmptyText.changeVisibility(addresses.isEmpty())
    }

    private fun onOrderCreated() {
        navigator.navigateToOrderSuccess(this)
    }

}