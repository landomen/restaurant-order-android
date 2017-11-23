package si.lanisnik.restaurantorder.remote.order.model

import si.lanisnik.restaurantorder.domain.model.order.OrderStatusEnum

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderHistoryDto(val id: Int,
                           val total: Double,
                           val createdAt: Long,
                           val itemsCount: Int,
                           val status: OrderStatusEnum)