package si.lanisnik.restaurantorder.ui.dashboard.model

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import si.lanisnik.restaurantorder.R

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
sealed class DashboardButtonModel(@StringRes val title: Int, @DrawableRes val icon: Int) {

    class Order : DashboardButtonModel(R.string.dashboard_order, R.drawable.ic_shopping_cart)
    class History : DashboardButtonModel(R.string.dashboard_history, R.drawable.ic_list)
    class Profile : DashboardButtonModel(R.string.dashboard_profile, R.drawable.ic_person)
    class Login : DashboardButtonModel(R.string.dashboard_login, R.drawable.ic_person)
    class About : DashboardButtonModel(R.string.dashboard_about, R.drawable.ic_info_outline)

}