package si.lanisnik.restaurantorder.data.entity.orders.menuitem

import si.lanisnik.restaurantorder.data.net.model.menuitem.MenuItemDto
import si.lanisnik.restaurantorder.data.entity.orders.Order

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderMenuItems(
        val menuItemDto: MenuItemDto,
        val order: Order,
        val count: Int,
        val comment: String? = null
)