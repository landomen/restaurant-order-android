package si.lanisnik.restaurantorder.data.entity.orders.status

import si.lanisnik.restaurantorder.data.entity.orders.Order
import java.util.*


data class OrderStatusChanges(
        var status: OrderStatus,
        var order: Order,
        var changeTime: Date,
        var reason: String? = null,
        var estimatedDeliveryTime: Date? = null
)