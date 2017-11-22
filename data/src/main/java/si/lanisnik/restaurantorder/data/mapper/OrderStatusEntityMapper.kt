package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderStatusEntity
import si.lanisnik.restaurantorder.domain.model.order.OrderStatus
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderStatusEntityMapper @Inject constructor() : EntityMapper<OrderStatusEntity, OrderStatus> {

    override fun mapFromEntity(model: OrderStatusEntity): OrderStatus {
        return OrderStatus(model.status, model.changeTime, model.reason, model.estimatedDeliveryTime)
    }

    override fun mapToEntity(model: OrderStatus): OrderStatusEntity {
        throw UnsupportedOperationException()
    }

}