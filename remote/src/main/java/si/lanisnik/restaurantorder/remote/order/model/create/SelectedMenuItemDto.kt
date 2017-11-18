package si.lanisnik.restaurantorder.remote.order.model.create

import si.lanisnik.restaurantorder.remote.menuitem.model.MenuItemDto

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class SelectedMenuItemDto(
        val id: Long,
        val menuItem: MenuItemDto,
        var comment: String?
)