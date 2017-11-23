package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderHistoryEntity
import si.lanisnik.restaurantorder.domain.model.order.OrderHistory
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryEntityMapper @Inject constructor() : EntityMapper<OrderHistoryEntity, OrderHistory> {

    override fun mapFromEntity(model: OrderHistoryEntity): OrderHistory {
        return OrderHistory(
                model.id,
                model.total,
                model.createdAt,
                model.itemsCount,
                model.status)
    }

    override fun mapToEntity(model: OrderHistory): OrderHistoryEntity {
        throw UnsupportedOperationException()
    }

}