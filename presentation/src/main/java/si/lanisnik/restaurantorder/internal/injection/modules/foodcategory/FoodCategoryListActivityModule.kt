package si.lanisnik.restaurantorder.internal.injection.modules.foodcategory

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.domain.interactor.foodcategory.GetCategories
import si.lanisnik.restaurantorder.ui.foodcategory.CategoriesListViewModelFactory
import si.lanisnik.restaurantorder.mapper.FoodCategoryMapper
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity

/**
 * Module used to provide dependencies to a specific activity.
 */
@Module
open class FoodCategoryListActivityModule {

    @PerActivity
    @Provides
    fun provideCategoriesListViewModelFactory(getCategories: GetCategories, mapper: FoodCategoryMapper): CategoriesListViewModelFactory =
            CategoriesListViewModelFactory(getCategories, mapper)

}