package si.lanisnik.restaurantorder.ui.menuitem.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.ui.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.menuitem.list.holder.MenuItemViewHolder
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuitemRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<MenuItemViewHolder>() {

    var listener: OnMenuItemSelectedListener? = null
    var items: List<MenuItemModel> by Delegates.observable(listOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menuItem = items[position]
        holder.bindModel(menuItem)
        holder.itemView.setOnClickListener {
            listener?.onMenuItemSelected(menuItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder =
            MenuItemViewHolder(parent.inflate(R.layout.recycler_item_menu_item))

    interface OnMenuItemSelectedListener {
        fun onMenuItemSelected(item: MenuItemModel)
    }
}