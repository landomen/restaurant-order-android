package si.lanisnik.restaurantorder.presentation.categories.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recycler_item_category.view.*
import si.lanisnik.restaurantorder.data.entity.menuitems.FoodCategory

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class CategoryViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bind(category: FoodCategory) {
        itemView.categoryItemNameText.text = category.title
    }
}