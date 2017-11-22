package si.lanisnik.restaurantorder.data.entity.orders

import si.lanisnik.restaurantorder.domain.model.order.OrderStatusEnum

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderStatusEntity(val status: OrderStatusEnum,
                             val changeTime: Long,
                             val reason: String?,
                             val estimatedDeliveryTime: Long?)