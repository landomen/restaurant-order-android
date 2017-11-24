package si.lanisnik.restaurantorder.ui.order.detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.order.detail.viewholder.OrderStatusViewHolder
import si.lanisnik.restaurantorder.ui.order.model.OrderStatusModel
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 24/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderStatusRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<OrderStatusViewHolder>() {

    var items: List<OrderStatusModel> by Delegates.observable(listOf(), { _, _, _ -> notifyDataSetChanged() })

    override fun onBindViewHolder(holder: OrderStatusViewHolder, position: Int) {
        holder.bindModel(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        return OrderStatusViewHolder(parent.inflate(R.layout.recycler_item_order_status))
    }

    override fun getItemCount(): Int = items.size
}