package si.lanisnik.restaurantorder.ui.order.shoppingcart.viewholder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_shopping_cart.view.*
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.setTextOrDefault
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class ShoppingCartItemViewHolder(itemView: View) : BaseViewHolder<ShoppingCartItemModel>(itemView) {

    override fun bindModel(model: ShoppingCartItemModel) {
        itemView.shoppingCartItemTitle.text = model.menuItem.title
        itemView.shoppingCartItemComment.setTextOrDefault(model.comment, R.string.menu_item_no_comment)
        itemView.shoppingCartItemPrice.text = model.menuItem.getFormattedPrice()
    }

    fun onDeleteClick(listener: (View) -> Unit) {
        itemView.shoppingCartItemRemoveIcon.setOnClickListener(listener)
    }
}