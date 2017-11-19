package si.lanisnik.restaurantorder.ui.order.shoppingcart

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_preview.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.order.navigator.OrderNavigator
import si.lanisnik.restaurantorder.ui.order.shoppingcart.adapter.ShoppingCartRecyclerAdapter
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderPreviewActivity : BaseActivity() {

    @Inject lateinit var adapter: ShoppingCartRecyclerAdapter
    @Inject lateinit var viewModelFactory: OrderPreviewViewModelFactory
    @Inject lateinit var orderNavigator: OrderNavigator
    private lateinit var viewModel: OrderPreviewViewModel

    override fun getContentView(): Int = R.layout.activity_order_preview

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.shopping_cart_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        adapter.deleteListener = {
            viewModel.removeItem(it)
        }
        shoppingCartRecyclerView.adapter = adapter
        shoppingCartRecyclerView.enableItemDividers()
        shoppingCartContinueButton.setOnClickListener {
            viewModel.onNextStepClicked()
        }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        viewModel.getItemsObservable().observe(this, Observer {
            onDataReceived(it!!)
        })
        viewModel.getTotalCostObservable().observe(this, Observer {
            shoppingCartTotalPriceGroup.show()
            shoppingCartTotalText.text = it
        })
        viewModel.getCanContinueObservable().observe(this, Observer {
            onCanContinueToNextStep(it!!)
        })
    }

    private fun onDataReceived(items: List<ShoppingCartItemModel>) {
        val itemsNotEmpty = items.isNotEmpty()
        if (itemsNotEmpty) {
            adapter.updateItems(items)
        }
        shoppingCartEmptyText.changeVisibility(!itemsNotEmpty)
        shoppingCartRecyclerView.changeVisibility(itemsNotEmpty)
        shoppingCartContinueButton.changeVisibility(itemsNotEmpty)
        shoppingCartTotalPriceGroup.changeVisibility(itemsNotEmpty)
    }

    private fun onCanContinueToNextStep(canContinue: Boolean) {
        if (canContinue) {
            orderNavigator.navigateToSendOrder(this)
        } else {
            shoppingCartContinueButton.snackbar(R.string.send_order_not_logged_in)
        }
    }
}