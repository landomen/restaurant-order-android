package si.lanisnik.restaurantorder.menuitem.list.holder

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_menu_item.view.*
import si.lanisnik.restaurantorder.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemViewHolder(itemView: View) : BaseViewHolder<MenuItemModel>(itemView) {

    private val titleTextView by lazy { itemView.itemMenuItemTitle }
    private val descriptionTextView by lazy { itemView.itemMenuItemDescription }
    private val priceTextView by lazy { itemView.itemMenuItemDescription }

    override fun bindModel(model: MenuItemModel) {
        titleTextView.text = model.title
        descriptionTextView.text = model.description
        priceTextView.text = "${model.price}€"
    }
}