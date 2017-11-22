package si.lanisnik.restaurantorder.remote.order.model.status

import si.lanisnik.restaurantorder.domain.model.order.OrderStatusEnum

data class OrderStatusDto(
        val status: OrderStatusEnum,
        val changeTime: Long,
        val reason: String?,
        val estimatedDeliveryTime: Long?
)