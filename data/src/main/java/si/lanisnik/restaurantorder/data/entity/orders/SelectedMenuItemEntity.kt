package si.lanisnik.restaurantorder.data.entity.orders

import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Created by Domen Lanišnik on 16/11/2017.
 * domen.lanisnik@gmail.com
 */
data class SelectedMenuItemEntity(val id: Long, val menuItem: MenuItemEntity, var comment: String?)