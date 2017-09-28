package si.lanisnik.restaurantorder.categories

import android.os.Bundle
import android.support.annotation.Nullable
import dagger.android.AndroidInjection
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
import si.lanisnik.restaurantorder.data.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.domain.model.menuitem.FoodCategory
import si.lanisnik.restaurantorder.ui.categories.CategoriesListPresenter
import javax.inject.Inject

class CategoriesListActivity : BaseActivity() {

//    private lateinit var categories: List<GroupedByCategory>

    @Inject
    lateinit var presenter: CategoriesListPresenter

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)
        setSupportActionBar(toolbar)
        setTitle(R.string.categories_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        fetchData()

        AndroidInjection.inject(this)

        presenter.onStart()
    }

    private fun fetchData() {
        toggleLoading(true)


//        RestaurantOrderServiceFactory.menuItemsApi.getAllMenuItems()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ response ->
//                    categories = response.categories
//                    showCategories()
//                    toggleLoading(false)
//                }, {
//                    showFetchingError()
//                })
    }

    private fun showCategories() {
//        categoriesRecyclerView.adapter = CategoryRecyclerAdapter(categories.map { FoodCategory(title = it.category.title) }, {
//
//        })
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
