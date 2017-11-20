package si.lanisnik.restaurantorder.ui.order.history

import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.extensions.createViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.enableBackArrow
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: OrderHistoryListViewModelFactory
    private lateinit var viewModel: OrderHistoryListViewModel

    override fun getContentView(): Int = R.layout.activity_order_history_list

    override fun initToolbar() {

    }

    override fun initUi() {
        setSupportActionBar(toolbar)
        setTitle(R.string.history_title)
        enableBackArrow()
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initViewModel() {
        viewModel = createViewModel(viewModelFactory)
    }

    override fun setupObservers() {

    }
}