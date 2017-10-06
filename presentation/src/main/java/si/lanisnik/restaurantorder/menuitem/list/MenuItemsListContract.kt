package si.lanisnik.restaurantorder.menuitem.list

import si.lanisnik.restaurantorder.base.BasePresenter
import si.lanisnik.restaurantorder.base.BaseView
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel

/**
 * Created by Domen Lanišnik on 06/10/2017.
 * domen.lanisnik@gmail.com
 */
interface MenuItemsListContract {

    interface View : BaseView {
        fun showMenuItems(menuItems: List<MenuItemModel>)
    }

    interface Presenter : BasePresenter<View> {
        fun initialize(categoryId: Int)
    }
}