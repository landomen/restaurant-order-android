package si.lanisnik.restaurantorder.menuitem.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_items_list.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.intentFor
import org.parceler.Parcels
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.BaseActivity
import si.lanisnik.restaurantorder.base.data.Resource
import si.lanisnik.restaurantorder.base.data.ResourceState
import si.lanisnik.restaurantorder.base.extensions.enableItemDividers
import si.lanisnik.restaurantorder.base.extensions.hide
import si.lanisnik.restaurantorder.base.extensions.isNotNullAndEmpty
import si.lanisnik.restaurantorder.base.extensions.show
import si.lanisnik.restaurantorder.base.views.LoadingStateView
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.menuitem.list.adapter.MenuitemRecyclerAdapter
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

class MenuItemsListActivity : BaseActivity(), MenuitemRecyclerAdapter.OnMenuItemSelectedListener, LoadingStateView.RetryListener {

    companion object {
        private const val EXTRA_FOOD_CATEGORY = "si.lanisnik.restaurantorder.menuitem.list.FoodCategory"

        /**
         * Create intent to open [MenuItemsListActivity] based on the selected category.
         */
        fun create(context: Context, foodCategory: FoodCategoryModel): Intent =
                context.intentFor<MenuItemsListActivity>(EXTRA_FOOD_CATEGORY to Parcels.wrap(foodCategory))
    }

    @Inject lateinit var adapter: MenuitemRecyclerAdapter
    @Inject lateinit var viewModelFactory: MenuItemsListViewModelFactory
    private lateinit var viewModel: MenuItemListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MenuItemListViewModel::class.java]
        initViewModel()
        setupObservers()
    }

    override fun getContentView(): Int = R.layout.activity_menu_items_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        initRecyclerView()
        menuItemLoadingStateView.retryListener = this
    }

    override fun onMenuItemSelected(id: Int) {
        // TODO Open details
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    private fun initViewModel() {
        val foodCategory = Parcels.unwrap<FoodCategoryModel>(intent.getParcelableExtra(EXTRA_FOOD_CATEGORY))
        setToolbarTitle(foodCategory.title)
        viewModel.initialize(foodCategory)
    }

    private fun setToolbarTitle(title: String) {
        setTitle(title)
    }

    private fun initRecyclerView() {
        adapter.listener = this
        menuItemsRecyclerView.adapter = adapter
        menuItemsRecyclerView.enableItemDividers()
    }

    private fun setupObservers() {
        viewModel.getMenuItems().observe(this, Observer<Resource<List<MenuItemModel>>> {
            it?.let { handleDataState(it.status, it.data) }
        })
    }

    private fun handleDataState(state: ResourceState, data: List<MenuItemModel>?) {
        when (state) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showMenuItems(data)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showMenuItems(menuItems: List<MenuItemModel>?) {
        if (menuItems.isNotNullAndEmpty()) {
            adapter.items = menuItems!!
            menuItemLoadingStateView.hide()
        } else {
            showLoadingState(LoadingStateView.State.EMPTY)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        menuItemLoadingStateView.state = state
        menuItemLoadingStateView.show()
    }
}
