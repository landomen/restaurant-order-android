package si.lanisnik.restaurantorder.data.entity.orders

import si.lanisnik.restaurantorder.data.entity.customer.Customer
import si.lanisnik.restaurantorder.data.entity.orders.menuitem.OrderMenuItems
import si.lanisnik.restaurantorder.data.entity.orders.status.OrderStatusChanges

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class Order(
        val id: Int,
        val total: Double,
        val customerComment: String? = null,
        val customer: Customer,
        val menuItems: List<OrderMenuItems> = listOf(),
        val orderChanges: List<OrderStatusChanges> = listOf()
)