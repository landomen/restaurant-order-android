package si.lanisnik.restaurantorder.ui.categories.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.base.extensions.inflate
import si.lanisnik.restaurantorder.ui.categories.holders.CategoryViewHolder
import si.lanisnik.restaurantorder.ui.categories.model.FoodCategoryModel
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class CategoriesRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<CategoryViewHolder>() {

    var listener: OnCategoryClickListener? = null
    var categories: List<FoodCategoryModel> by Delegates.observable(listOf()) { _, _, new ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            listener?.onCategoryClicked(category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
            CategoryViewHolder(parent.inflate(R.layout.recycler_item_category))

    interface OnCategoryClickListener {
        fun onCategoryClicked(category: FoodCategoryModel)
    }
}