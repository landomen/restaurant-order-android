package si.lanisnik.restaurantorder.ui.dashboard.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import si.lanisnik.restaurantorder.ui.dashboard.model.DashboardButtonModel
import si.lanisnik.restaurantorder.ui.dashboard.view.DashboardButton

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardButtonsAdapter private constructor(private val buttons: List<DashboardButtonModel>,
                                                  private val listener: (DashboardButtonModel) -> Unit) : BaseAdapter() {

    companion object {
        fun create(full: Boolean, listener: (DashboardButtonModel) -> Unit): DashboardButtonsAdapter {
            val buttons = if (full) {
                listOf(DashboardButtonModel.Order(),
                        DashboardButtonModel.History(),
                        DashboardButtonModel.Profile(),
                        DashboardButtonModel.About())
            } else {
                listOf(DashboardButtonModel.Order(),
                        DashboardButtonModel.Login(),
                        DashboardButtonModel.About())
            }
            return DashboardButtonsAdapter(buttons, listener)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val buttonView = if (convertView == null)
            DashboardButton(parent.context)
        else
            convertView as DashboardButton

        with(getItem(position)) {
            buttonView.setIconAndTitle(this.icon, this.title)
            buttonView.setOnClickListener {
                listener.invoke(this)
            }
        }

        return buttonView
    }

    override fun getItem(position: Int): DashboardButtonModel = buttons[position]
    override fun getItemId(position: Int): Long = 0
    override fun getCount(): Int = buttons.size

}