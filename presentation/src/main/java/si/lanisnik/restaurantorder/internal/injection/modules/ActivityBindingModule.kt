package si.lanisnik.restaurantorder.internal.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import si.lanisnik.restaurantorder.foodcategory.CategoriesListActivity
import si.lanisnik.restaurantorder.internal.injection.modules.foodcategory.FoodCategoryListActivityModule
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.menuitem.list.MenuItemListModule
import si.lanisnik.restaurantorder.menuitem.list.MenuItemsListActivity

/**
 * Created by Domen Lanišnik on 27/09/2017.
 * domen.lanisnik@gmail.com
 */
@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(FoodCategoryListActivityModule::class))
    abstract fun categoriesListActivity(): CategoriesListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MenuItemListModule::class))
    abstract fun menuItemListActivity(): MenuItemsListActivity
}