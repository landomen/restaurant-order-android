package si.lanisnik.restaurantorder.remote.order.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderStatusEntity
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.order.model.status.OrderStatusDto
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderStatusRemoteMapper @Inject constructor() : RemoteEntityMapper<OrderStatusDto, OrderStatusEntity> {

    override fun mapFromRemote(remoteModel: OrderStatusDto): OrderStatusEntity {
        return OrderStatusEntity(
                remoteModel.status,
                remoteModel.changeTime,
                remoteModel.reason,
                remoteModel.estimatedDeliveryTime)
    }
}