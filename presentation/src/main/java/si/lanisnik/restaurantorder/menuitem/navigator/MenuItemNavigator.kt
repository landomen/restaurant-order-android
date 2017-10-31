package si.lanisnik.restaurantorder.menuitem.navigator

import android.content.Context
import org.jetbrains.anko.startActivity
import org.parceler.Parcels
import si.lanisnik.restaurantorder.base.constants.ActivityConstants.EXTRA_FOOD_CATEGORY
import si.lanisnik.restaurantorder.base.constants.ActivityConstants.EXTRA_MENU_ITEM
import si.lanisnik.restaurantorder.foodcategory.model.FoodCategoryModel
import si.lanisnik.restaurantorder.menuitem.details.MenuItemDetailsActivity
import si.lanisnik.restaurantorder.menuitem.list.MenuItemsListActivity
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemNavigator @Inject constructor() {

    fun navigateToDetails(context: Context, menuItem: MenuItemModel) {
        context.startActivity<MenuItemDetailsActivity>(EXTRA_MENU_ITEM to Parcels.wrap(menuItem))
    }

    fun navigateToList(context: Context, foodCategory: FoodCategoryModel) {
        context.startActivity<MenuItemsListActivity>(EXTRA_FOOD_CATEGORY to Parcels.wrap(foodCategory))
    }
}