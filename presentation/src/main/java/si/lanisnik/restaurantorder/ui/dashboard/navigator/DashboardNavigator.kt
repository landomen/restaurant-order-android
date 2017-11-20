package si.lanisnik.restaurantorder.ui.dashboard.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.ui.about.AboutActivity
import si.lanisnik.restaurantorder.ui.customer.profile.ProfileActivity
import si.lanisnik.restaurantorder.ui.foodcategory.CategoriesListActivity
import si.lanisnik.restaurantorder.ui.onboarding.login.LoginActivity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardNavigator @Inject constructor() {

    fun navigateToCategories(context: Context) {
        context.startActivity<CategoriesListActivity>()
    }

    fun navigateToProfile(context: Context) {
        context.startActivity<ProfileActivity>()
    }

    fun navigateToLogin(context: Context) {
        context.startActivity<LoginActivity>()
    }

    fun navigateToHistory(context: Context) {
        // TODO
    }

    fun navigateToAbout(context: Context) {
        context.startActivity<AboutActivity>()
    }

}