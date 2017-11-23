package si.lanisnik.restaurantorder.ui.order.history.viewholder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_order_history.view.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.ui.order.model.OrderHistoryModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryViewHolder(itemView: View) : BaseViewHolder<OrderHistoryModel>(itemView) {

    override fun bindModel(model: OrderHistoryModel) {
        itemView.orderHistoryPriceText.text = "${model.total}€"
        itemView.orderHistoryTimeText.text = SimpleDateFormat("dd.MM.yyyy 'ob' HH:mm", Locale.GERMAN).format(Date(model.createdAt))
        itemView.orderHistoryStatusText.setText(model.status.resId)
        itemView.orderHistoryCountText.text = itemView.context.getString(R.string.history_items_count, model.itemsCount)
    }

}