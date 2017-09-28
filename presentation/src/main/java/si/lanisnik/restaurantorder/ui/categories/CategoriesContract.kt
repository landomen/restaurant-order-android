package si.lanisnik.restaurantorder.ui.categories

import si.lanisnik.restaurantorder.ui.base.BasePresenter
import si.lanisnik.restaurantorder.ui.base.BaseView
import si.lanisnik.restaurantorder.ui.categories.model.FoodCategoryModel

/**
 * Created by Domen Lanišnik on 26/09/2017.
 * domen.lanisnik@gmail.com
 */
interface CategoriesContract {

    interface View : BaseView {
        fun showCategories(categories: List<FoodCategoryModel>)
    }

    interface Presenter : BasePresenter<View> {

    }
}