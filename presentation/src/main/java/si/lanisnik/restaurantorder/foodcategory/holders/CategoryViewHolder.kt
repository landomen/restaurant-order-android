package si.lanisnik.restaurantorder.foodcategory.holders

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_food_category.view.*
import si.lanisnik.restaurantorder.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
class CategoryViewHolder(itemView: View) : BaseViewHolder<FoodCategoryModel>(itemView) {

    private val nameTextView by lazy { itemView.categoryItemNameText }

    override fun bindModel(model: FoodCategoryModel) {
        nameTextView.text = model.title
    }
}