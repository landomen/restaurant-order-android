package si.lanisnik.restaurantorder.ui.foodcategory

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Nullable
import kotlinx.android.synthetic.main.activity_categories_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.BaseActivity
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.ResourceState
import si.lanisnik.restaurantorder.ui.base.extensions.*
import si.lanisnik.restaurantorder.ui.base.views.LoadingStateView
import si.lanisnik.restaurantorder.ui.foodcategory.adapters.CategoriesRecyclerAdapter
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.ui.menuitem.navigator.MenuItemNavigator
import javax.inject.Inject

class CategoriesListActivity : BaseActivity(), CategoriesRecyclerAdapter.OnCategoryClickListener, LoadingStateView.RetryListener {

    @Inject lateinit var adapter: CategoriesRecyclerAdapter
    @Inject lateinit var viewModelFactory: CategoriesListViewModelFactory
    @Inject lateinit var menuItemNavigator: MenuItemNavigator

    private lateinit var viewModel: CategoriesListViewModel

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[CategoriesListViewModel::class.java]
        setupObservers()
    }

    override fun getContentView(): Int = R.layout.activity_categories_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        enableBackArrow()
        toolbar.setTitle(R.string.categories_title)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initUi() {
        setupRecyclerView()
        categoriesLoadingStateView.retryListener = this
    }

    override fun onCategoryClicked(category: FoodCategoryModel) {
        menuItemNavigator.navigateToList(this, category)
    }

    override fun onRetryClicked() {
        viewModel.retry()
    }

    private fun setupObservers() {
        viewModel.getCategories().observe(this, Observer<Resource<List<FoodCategoryModel>>> {
            it?.let { handleDataState(it.status, it.data) }
        })
    }

    private fun handleDataState(state: ResourceState, data: List<FoodCategoryModel>?) {
        when (state) {
            ResourceState.LOADING -> showLoadingState(LoadingStateView.State.LOADING)
            ResourceState.SUCCESS -> showCategories(data)
            ResourceState.ERROR -> showLoadingState(LoadingStateView.State.ERROR)
        }
    }

    private fun showCategories(categories: List<FoodCategoryModel>?) {
        if (categories.isNotNullAndEmpty()) {
            adapter.categories = categories!!
            categoriesLoadingStateView.hide()
        } else {
            showLoadingState(LoadingStateView.State.EMPTY)
        }
    }

    private fun showLoadingState(state: LoadingStateView.State) {
        categoriesLoadingStateView.state = state
        categoriesLoadingStateView.show()
    }

    private fun setupRecyclerView() {
        adapter.listener = this
        categoriesRecyclerView.adapter = adapter
        categoriesRecyclerView.enableItemDividers()
    }
}
