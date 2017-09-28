package si.lanisnik.restaurantorder.ui.categories

import android.os.Bundle
import android.support.annotation.Nullable
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_categories_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.BaseActivity
import si.lanisnik.restaurantorder.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.base.extensions.enableItemDividers
import si.lanisnik.restaurantorder.base.extensions.showErrorDialogWithRetryAndDismissCallback
import si.lanisnik.restaurantorder.ui.categories.adapters.CategoriesRecyclerAdapter
import si.lanisnik.restaurantorder.ui.categories.model.FoodCategoryModel
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
    }

    override fun showCategories(categories: List<FoodCategoryModel>) {
        adapter.categories = categories
        adapter.listener = this
    }

    override fun onCategoryClicked(category: FoodCategoryModel) {
        // TODO Open menu items list activity
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
