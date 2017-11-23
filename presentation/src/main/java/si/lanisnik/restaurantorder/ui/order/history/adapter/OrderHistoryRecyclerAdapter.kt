package si.lanisnik.restaurantorder.ui.order.history.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.order.history.viewholder.OrderHistoryViewHolder
import si.lanisnik.restaurantorder.ui.order.model.OrderHistoryModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<OrderHistoryViewHolder>() {

    var orders = listOf<OrderHistoryModel>()
    var listener: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        val order = orders[position]
        holder.bindModel(order)
        holder.itemView.setOnClickListener {
            listener?.invoke(order.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        return OrderHistoryViewHolder(parent.inflate(R.layout.recycler_item_order_history))
    }

    override fun getItemCount(): Int = orders.size

}