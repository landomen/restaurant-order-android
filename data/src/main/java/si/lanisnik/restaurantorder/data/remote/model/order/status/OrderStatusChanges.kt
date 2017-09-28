package si.lanisnik.restaurantorder.data.remote.model.order.status

import si.lanisnik.restaurantorder.data.remote.model.order.Order
import java.util.*


data class OrderStatusChanges(
        var status: OrderStatus,
        var order: Order,
        var changeTime: Date,
        var reason: String? = null,
        var estimatedDeliveryTime: Date? = null
)