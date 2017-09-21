package si.lanisnik.restaurantorder.categories.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.extensions.inflate
import si.lanisnik.restaurantorder.categories.holders.CategoryViewHolder
import si.lanisnik.restaurantorder.domain.model.FoodCategory

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class CategoryRecyclerAdapter(var items: List<FoodCategory> = mutableListOf(),
                              private val itemListener: (position: Int) -> Unit) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { itemListener(position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
            CategoryViewHolder(parent.inflate(R.layout.recycler_item_category))
}