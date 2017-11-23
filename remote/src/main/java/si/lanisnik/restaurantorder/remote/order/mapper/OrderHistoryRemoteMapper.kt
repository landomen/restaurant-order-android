package si.lanisnik.restaurantorder.remote.order.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderHistoryEntity
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.order.model.OrderHistoryDto

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryRemoteMapper @javax.inject.Inject constructor() : RemoteEntityMapper<OrderHistoryDto, OrderHistoryEntity> {

    override fun mapFromRemote(remoteModel: OrderHistoryDto): OrderHistoryEntity {
        return OrderHistoryEntity(
                remoteModel.id,
                remoteModel.total,
                remoteModel.createdAt,
                remoteModel.itemsCount,
                remoteModel.status)
    }

}