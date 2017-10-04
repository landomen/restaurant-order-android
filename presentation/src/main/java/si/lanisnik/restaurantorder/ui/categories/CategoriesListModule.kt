package si.lanisnik.restaurantorder.ui.categories

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.internal.di.scopes.PerActivity

/**
 * Module used to provide dependencies to a specific activity.
 */
@Module
open class CategoriesListModule {

    @PerActivity
    @Provides
    fun provideCategoriesListView(categoriesListActivity: CategoriesListActivity): CategoriesContract.View =
            categoriesListActivity

    @PerActivity
    @Provides
    fun provideCategoriesListPresenter(view: CategoriesContract.View, getCategories: GetCategories): CategoriesContract.Presenter =
            CategoriesListPresenter(view, getCategories)

}