package si.lanisnik.restaurantorder.remote.order.model.status

import si.lanisnik.restaurantorder.remote.order.model.Order
import java.util.*


data class OrderStatusChanges(
        var status: OrderStatus,
        var order: Order,
        var changeTime: Date,
        var reason: String? = null,
        var estimatedDeliveryTime: Date? = null
)