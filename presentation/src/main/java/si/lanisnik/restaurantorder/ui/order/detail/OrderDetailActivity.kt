package si.lanisnik.restaurantorder.ui.order.detail

import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderDetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_ORDER_ID = "si.lanisnik.restaurantorder.ui.order.detail.OrderId"
    }

    @Inject lateinit var viewModelFactory: OrderDetailViewModelFactory
    private lateinit var viewModel: OrderDetailViewModel

    override fun getContentView(): Int = R.layout.activity_order_detail

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.order_detail_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {

    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
        viewModel.initialize(intent.getIntExtra(EXTRA_ORDER_ID, 0))
    }

    override fun setupObservers() {
        viewModel.getOrderObservable().observe(this, Observer {

        })
    }
}