package si.lanisnik.restaurantorder.ui.order.history.viewholder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_order_history.view.*
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.ui.order.model.OrderModel

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryViewHolder(itemView: View) : BaseViewHolder<OrderModel>(itemView) {

    override fun bindModel(model: OrderModel) {
        itemView.orderHistoryPriceText.text = "${model.total}€"
        itemView.orderHis
    }

}