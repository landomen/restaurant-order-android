package si.lanisnik.restaurantorder.ui.order.detail.viewholder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_order_status.view.*
import si.lanisnik.restaurantorder.ui.base.extensions.hide
import si.lanisnik.restaurantorder.ui.base.extensions.show
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.ui.order.model.OrderStatusModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Domen Lanišnik on 24/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderStatusViewHolder(itemView: View) : BaseViewHolder<OrderStatusModel>(itemView) {

    override fun bindModel(model: OrderStatusModel) {
        itemView.orderStatusText.setText(model.status.resId)
        itemView.orderStatusTimeText.text = SimpleDateFormat("dd.MM.yyyy 'ob' HH:mm", Locale.GERMAN).format(Date(model.changeTime))
        if (!model.reason.isNullOrEmpty()) {
            itemView.orderStatusReasonText.text = model.reason
            itemView.orderStatusReasonText.show()
        } else {
            itemView.orderStatusReasonText.hide()
        }
    }

}