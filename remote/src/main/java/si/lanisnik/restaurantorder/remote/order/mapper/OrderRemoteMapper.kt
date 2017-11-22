package si.lanisnik.restaurantorder.remote.order.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderEntity
import si.lanisnik.restaurantorder.remote.address.mapper.AddressRemoteMapper
import si.lanisnik.restaurantorder.remote.base.mapper.RemoteEntityMapper
import si.lanisnik.restaurantorder.remote.order.model.OrderDto
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderRemoteMapper @Inject constructor(private val statusMapper: OrderStatusRemoteMapper,
                                            private val addressMapper: AddressRemoteMapper,
                                            private val selectedMenuItemMapper: SelectedMenuItemRemoteMapper) :
        RemoteEntityMapper<OrderDto, OrderEntity> {

    override fun mapFromRemote(remoteModel: OrderDto): OrderEntity {
        return OrderEntity(
                remoteModel.id,
                remoteModel.total,
                remoteModel.customerComment,
                addressMapper.mapFromRemote(remoteModel.address),
                remoteModel.menuItems.map { selectedMenuItemMapper.mapFromRemote(it) },
                remoteModel.orderChanges.map { statusMapper.mapFromRemote(it) })
    }

}