package si.lanisnik.restaurantorder.foodcategory

import android.os.Bundle
import android.support.annotation.Nullable
import kotlinx.android.synthetic.main.activity_categories_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.BaseActivity
import si.lanisnik.restaurantorder.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.base.extensions.enableItemDividers
import si.lanisnik.restaurantorder.base.extensions.showErrorDialogWithRetryAndDismissCallback
import si.lanisnik.restaurantorder.foodcategory.adapters.CategoriesRecyclerAdapter
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.menuitem.list.MenuItemsListActivity
import javax.inject.Inject

class CategoriesListActivity : BaseActivity(), CategoriesContract.View, CategoriesRecyclerAdapter.OnCategoryClickListener {

    @Inject lateinit var presenter: CategoriesListPresenter
    @Inject lateinit var adapter: CategoriesRecyclerAdapter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onStart()
    }

    override fun getContentView(): Int = R.layout.activity_categories_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        setTitle(R.string.categories_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initUi() {
        setupRecyclerView()
        toggleLoading(true)
    }

    override fun showCategories(categories: List<FoodCategoryModel>) {
        adapter.categories = categories
        adapter.listener = this
        toggleLoading(false)
    }

    override fun onCategoryClicked(category: FoodCategoryModel) {
        startActivity(MenuItemsListActivity.create(this, category.id))
    }

    private fun toggleLoading(visible: Boolean) {
        categoriesLoadingView.changeVisibility(visible)
    }

    private fun showFetchingError() {
        showErrorDialogWithRetryAndDismissCallback(R.string.error_loading_failed, {
//            fetchData()
        }, {
//            finish()
        })
    }

    private fun setupRecyclerView() {
        categoriesRecyclerView.adapter = adapter
        categoriesRecyclerView.enableItemDividers()
    }
}
