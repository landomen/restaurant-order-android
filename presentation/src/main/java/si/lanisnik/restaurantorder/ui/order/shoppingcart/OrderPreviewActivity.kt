package si.lanisnik.restaurantorder.ui.order.shoppingcart

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_order_preview.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import si.lanisnik.restaurantorder.ui.base.extensions.enableItemDividers
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
    private lateinit var viewModel: OrderPreviewViewModel

    override fun getContentView(): Int = R.layout.activity_order_preview

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.shopping_cart_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        adapter.deleteListener = {
            viewModel.removeItem(it)
        }
        shoppingCartRecyclerView.adapter = adapter
        shoppingCartRecyclerView.enableItemDividers()
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize()
    }

    override fun setupObservers() {
        viewModel.getItemsObservable().observe(this, Observer {
            onDataReceived(it!!)
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
}