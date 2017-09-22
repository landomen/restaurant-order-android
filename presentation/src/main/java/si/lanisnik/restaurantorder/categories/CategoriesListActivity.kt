package si.lanisnik.restaurantorder.categories

import android.os.Bundle
import android.support.annotation.Nullable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_categories_list.*
import kotlinx.android.synthetic.main.toolbar.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.BaseActivity
import si.lanisnik.restaurantorder.base.extensions.changeVisibility
import si.lanisnik.restaurantorder.base.extensions.enableItemDividers
import si.lanisnik.restaurantorder.base.extensions.showErrorDialogWithRetryAndDismissCallback
import si.lanisnik.restaurantorder.categories.adapters.CategoryRecyclerAdapter
import si.lanisnik.restaurantorder.data.entity.menuitems.GroupedByCategory
import si.lanisnik.restaurantorder.data.net.RestApiClient
import si.lanisnik.restaurantorder.domain.model.FoodCategory

class CategoriesListActivity : BaseActivity() {

    private lateinit var categories: List<GroupedByCategory>

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)
        setSupportActionBar(toolbar)
        setTitle(R.string.categories_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        fetchData()
    }

    private fun fetchData() {
        toggleLoading(true)
        RestApiClient.menuItemsApi.getAllMenuItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    categories = response.categories
                    showCategories()
                    toggleLoading(false)
                }, {
                    showFetchingError()
                })
    }

    private fun showCategories() {
        categoriesRecyclerView.adapter = CategoryRecyclerAdapter(categories.map { FoodCategory(title = it.title) }, {

        })
        categoriesRecyclerView.enableItemDividers()
    }

    private fun toggleLoading(visible: Boolean) {
        categoriesLoadingView.changeVisibility(visible)
    }

    private fun showFetchingError() {
        showErrorDialogWithRetryAndDismissCallback(R.string.error_loading_failed, {
            fetchData()
        }, {
            finish()
        })
    }
}
