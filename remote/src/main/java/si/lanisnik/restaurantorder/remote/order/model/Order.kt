package si.lanisnik.restaurantorder.remote.order.model

import si.lanisnik.restaurantorder.remote.customer.model.CustomerDto
import si.lanisnik.restaurantorder.remote.order.model.menuitem.OrderMenuItems
import si.lanisnik.restaurantorder.remote.order.model.status.OrderStatusChanges

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class Order(
        val id: Int,
        val total: Double,
        val customerComment: String? = null,
        val customerDto: CustomerDto,
        val menuItems: List<OrderMenuItems> = listOf(),
        val orderChanges: List<OrderStatusChanges> = listOf()
)