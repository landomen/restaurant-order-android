package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.order.OrderHistory
import si.lanisnik.restaurantorder.ui.order.model.OrderHistoryModel
import si.lanisnik.restaurantorder.ui.order.model.OrderStatusType
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryModelMapper @Inject constructor() : PresentationMapper<OrderHistory, OrderHistoryModel> {

    override fun mapToModel(model: OrderHistory): OrderHistoryModel {
        return OrderHistoryModel(
                model.id,
                model.total,
                model.createdAt,
                model.itemsCount,
                OrderStatusType.valueOf(model.status.name))
    }

}