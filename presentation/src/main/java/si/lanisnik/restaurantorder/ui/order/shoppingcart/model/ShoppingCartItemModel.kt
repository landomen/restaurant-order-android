package si.lanisnik.restaurantorder.ui.order.shoppingcart.model

import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
data class ShoppingCartItemModel(var id: Long, val menuItem: MenuItemModel, val comment: String?)