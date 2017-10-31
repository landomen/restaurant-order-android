package si.lanisnik.restaurantorder.ui.foodcategory.holders

import android.view.View
import kotlinx.android.synthetic.main.recycler_item_food_category.view.*
import si.lanisnik.restaurantorder.ui.base.holder.BaseViewHolder
import si.lanisnik.restaurantorder.ui.foodcategory.model.FoodCategoryModel

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