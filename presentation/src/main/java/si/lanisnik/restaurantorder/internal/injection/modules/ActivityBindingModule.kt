package si.lanisnik.restaurantorder.internal.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import si.lanisnik.restaurantorder.foodcategory.CategoriesListActivity
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.foodcategory.CategoriesListModule

/**
 * Created by Domen Lanišnik on 27/09/2017.
 * domen.lanisnik@gmail.com
 */
@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(CategoriesListModule::class))
    abstract fun categoriesListActivity(): CategoriesListActivity
}