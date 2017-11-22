package si.lanisnik.restaurantorder.remote.order.model.menuitem

import si.lanisnik.restaurantorder.remote.menuitem.model.MenuItemDto
import si.lanisnik.restaurantorder.remote.order.model.OrderDto

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderMenuItems(
        val menuItemDto: MenuItemDto,
        val order: OrderDto,
        val count: Int,
        val comment: String? = null
)