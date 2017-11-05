package si.lanisnik.restaurantorder.ui.dashboard.view

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.Gravity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_dashboard_button.view.*
import org.jetbrains.anko.imageResource
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.inflate

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardButton(context: Context) : LinearLayout(context) {

    init {
        inflate(R.layout.view_dashboard_button, true)
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    fun setIconAndTitle(@DrawableRes icon: Int, @StringRes title: Int) {
        dashboardButtonIcon.imageResource = icon
        dashboardButtonText.setText(title)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        dashboardButtonLayout.setOnClickListener(l)
    }

}