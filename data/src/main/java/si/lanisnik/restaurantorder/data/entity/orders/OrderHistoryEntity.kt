package si.lanisnik.restaurantorder.data.entity.orders

import si.lanisnik.restaurantorder.domain.model.order.OrderStatusEnum

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderHistoryEntity(val id: Int,
                              val total: Double,
                              val createdAt: Long,
                              val itemsCount: Int,
                              val status: OrderStatusEnum)