package si.lanisnik.restaurantorder.foodcategory

import si.lanisnik.restaurantorder.base.BasePresenter
import si.lanisnik.restaurantorder.base.BaseView
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel

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