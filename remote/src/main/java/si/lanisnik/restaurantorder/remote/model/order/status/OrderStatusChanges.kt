package si.lanisnik.restaurantorder.remote.model.order.status

import si.lanisnik.restaurantorder.remote.model.order.Order
import java.util.*


data class OrderStatusChanges(
        var status: OrderStatus,
        var order: Order,
        var changeTime: Date,
        var reason: String? = null,
        var estimatedDeliveryTime: Date? = null
)