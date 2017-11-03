package si.lanisnik.restaurantorder.internal.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import si.lanisnik.restaurantorder.internal.injection.modules.dashboard.DashboardActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.foodcategory.FoodCategoryListActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.menuitem.MenuItemActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.customer.LoginActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.customer.RegisterActivityModule
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.dashboard.DashboardActivity
import si.lanisnik.restaurantorder.ui.foodcategory.CategoriesListActivity
import si.lanisnik.restaurantorder.ui.menuitem.details.MenuItemDetailsActivity
import si.lanisnik.restaurantorder.ui.menuitem.list.MenuItemsListActivity
import si.lanisnik.restaurantorder.ui.onboarding.login.LoginActivity
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterActivity

/**
 * Created by Domen Lanišnik on 27/09/2017.
 * domen.lanisnik@gmail.com
 */
@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DashboardActivityModule::class))
    abstract fun dashboardActivity(): DashboardActivity

    // region Onboarding

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(LoginActivityModule::class))
    abstract fun loginActivity(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(RegisterActivityModule::class))
    abstract fun registerActivity(): RegisterActivity

    // endregion

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(FoodCategoryListActivityModule::class))
    abstract fun categoriesListActivity(): CategoriesListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MenuItemActivityModule::class))
    abstract fun menuItemListActivity(): MenuItemsListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MenuItemActivityModule::class))
    abstract fun menuItemDetailsActivity(): MenuItemDetailsActivity

}