package si.lanisnik.restaurantorder.internal.injection.modules.foodcategory

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.foodcategory.CategoriesListViewModelFactory
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity

/**
 * Module used to provide dependencies to a specific activity.
 */
@Module
open class FoodCategoryListActivityModule {

    @PerActivity
    @Provides
    fun provideCategoriesListViewModelFactory(getCategories: GetCategories): CategoriesListViewModelFactory =
            CategoriesListViewModelFactory(getCategories)

}