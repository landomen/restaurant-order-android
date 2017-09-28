package si.lanisnik.restaurantorder.data.remote.model.order

import si.lanisnik.restaurantorder.data.remote.model.customer.CustomerDto
import si.lanisnik.restaurantorder.data.remote.model.order.menuitem.OrderMenuItems
import si.lanisnik.restaurantorder.data.remote.model.order.status.OrderStatusChanges

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