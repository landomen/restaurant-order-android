package si.lanisnik.restaurantorder.ui.dashboard.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import si.lanisnik.restaurantorder.ui.foodcategory.CategoriesListActivity
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardNavigator @Inject constructor() {

    fun navigateToCategories(context: Context) {
        context.startActivity<CategoriesListActivity>()
    }

}