package si.lanisnik.restaurantorder.internal.injection.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import si.lanisnik.restaurantorder.internal.injection.modules.address.AddressesActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.customer.*
import si.lanisnik.restaurantorder.internal.injection.modules.dashboard.DashboardActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.foodcategory.FoodCategoryListActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.menuitem.MenuItemActivityModule
import si.lanisnik.restaurantorder.internal.injection.modules.order.OrderPreviewActivityModule
import si.lanisnik.restaurantorder.internal.injection.scopes.PerActivity
import si.lanisnik.restaurantorder.ui.address.AddressesActivity
import si.lanisnik.restaurantorder.ui.customer.password.ChangePasswordActivity
import si.lanisnik.restaurantorder.ui.customer.profile.ProfileActivity
import si.lanisnik.restaurantorder.ui.dashboard.DashboardActivity
import si.lanisnik.restaurantorder.ui.foodcategory.CategoriesListActivity
import si.lanisnik.restaurantorder.ui.menuitem.details.MenuItemDetailsActivity
import si.lanisnik.restaurantorder.ui.menuitem.list.MenuItemsListActivity
import si.lanisnik.restaurantorder.ui.onboarding.login.LoginActivity
import si.lanisnik.restaurantorder.ui.onboarding.password.ResetPasswordActivity
import si.lanisnik.restaurantorder.ui.onboarding.register.RegisterActivity
import si.lanisnik.restaurantorder.ui.order.shoppingcart.OrderPreviewActivity

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

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ResetPasswordActivityModule::class))
    abstract fun resetPasswordActivity(): ResetPasswordActivity

    // endregion

    // region Customer

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ProfileActivityModule::class))
    abstract fun profileActivity(): ProfileActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(ChangePasswordActivityModule::class))
    abstract fun changePasswordActivity(): ChangePasswordActivity

    // endregion

    // region Address

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(AddressesActivityModule::class))
    abstract fun addressesActivity(): AddressesActivity

    // endregion

    // region Category

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(FoodCategoryListActivityModule::class))
    abstract fun categoriesListActivity(): CategoriesListActivity

    // endregion

    // region Menu Item

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MenuItemActivityModule::class))
    abstract fun menuItemListActivity(): MenuItemsListActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MenuItemActivityModule::class))
    abstract fun menuItemDetailsActivity(): MenuItemDetailsActivity

    // endregion

    // region Order

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(OrderPreviewActivityModule::class))
    abstract fun orderPreviewActivity(): OrderPreviewActivity

    // endregion

}