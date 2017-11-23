package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.order.OrderStatus
import si.lanisnik.restaurantorder.ui.order.model.OrderStatusModel
import si.lanisnik.restaurantorder.ui.order.model.OrderStatusType
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderStatusModelMapper @Inject constructor() : PresentationMapper<OrderStatus, OrderStatusModel> {

    override fun mapToModel(model: OrderStatus): OrderStatusModel {
        return OrderStatusModel(OrderStatusType.valueOf(model.status.name), model.changeTime, model.reason, model.estimatedDeliveryTime)
    }

}