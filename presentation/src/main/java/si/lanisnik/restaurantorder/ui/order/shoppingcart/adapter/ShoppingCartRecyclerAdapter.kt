package si.lanisnik.restaurantorder.ui.order.shoppingcart.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel
import si.lanisnik.restaurantorder.ui.order.shoppingcart.viewholder.ShoppingCartItemViewHolder
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class ShoppingCartRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<ShoppingCartItemViewHolder>() {

    private var items = listOf<ShoppingCartItemModel>()
    var deleteListener: ((ShoppingCartItemModel) -> Unit)? = null

    override fun onBindViewHolder(holder: ShoppingCartItemViewHolder, position: Int) {
        val item = items[position]
        holder.bindModel(item)
        holder.onDeleteClick {
            deleteListener?.invoke(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartItemViewHolder {
        return ShoppingCartItemViewHolder(parent.inflate(R.layout.recycler_item_shopping_cart))
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<ShoppingCartItemModel>) {
        this.items = items
        notifyDataSetChanged()
    }

}