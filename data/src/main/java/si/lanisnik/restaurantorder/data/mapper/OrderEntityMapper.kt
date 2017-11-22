package si.lanisnik.restaurantorder.data.mapper

import si.lanisnik.restaurantorder.data.entity.orders.OrderEntity
import si.lanisnik.restaurantorder.domain.model.order.Order
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderEntityMapper @Inject constructor(private val addressMapper: AddressEntityMapper,
                                            private val selectedMenuItemMapper: SelectedMenuItemEntityMapper,
                                            private val statusMapper: OrderStatusEntityMapper) : EntityMapper<OrderEntity, Order> {
    override fun mapFromEntity(model: OrderEntity): Order {
        return Order(
                model.id,
                model.total,
                model.customerComment,
                addressMapper.mapFromEntity(model.address),
                model.menuItems.map { selectedMenuItemMapper.mapFromEntity(it) },
                model.orderChanges.map { statusMapper.mapFromEntity(it) }
        )
    }

    override fun mapToEntity(model: Order): OrderEntity {
        throw UnsupportedOperationException()
    }
}