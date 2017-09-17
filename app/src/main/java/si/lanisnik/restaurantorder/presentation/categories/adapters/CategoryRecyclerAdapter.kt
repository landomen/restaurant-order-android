package si.lanisnik.restaurantorder.presentation.categories.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.data.entity.menuitems.FoodCategory
import si.lanisnik.restaurantorder.presentation.base.extensions.inflate
import si.lanisnik.restaurantorder.presentation.categories.holders.CategoryViewHolder

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class CategoryRecyclerAdapter(var items: List<FoodCategory> = mutableListOf()) : RecyclerView.Adapter<CategoryViewHolder>() {



    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
            CategoryViewHolder(parent.inflate(R.layout.recycler_item_category))
}