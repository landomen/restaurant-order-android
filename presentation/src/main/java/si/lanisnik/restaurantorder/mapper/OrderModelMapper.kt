package si.lanisnik.restaurantorder.mapper

import si.lanisnik.restaurantorder.domain.model.order.Order
import si.lanisnik.restaurantorder.ui.order.model.OrderModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderModelMapper @Inject constructor(private val addressMapper: AddressModelMapper,
                                           private val menuItemMapper: SelectedMenuItemModelMapper,
                                           private val statusMapper: OrderStatusModelMapper) : PresentationMapper<Order, OrderModel> {

    override fun mapToModel(model: Order): OrderModel {
        return OrderModel(model.id,
                model.createdAt,
                model.total,
                model.customerComment,
                addressMapper.mapToModel(model.address),
                model.menuItems.map { menuItemMapper.mapToModel(it) },
                model.orderChanges.map { statusMapper.mapToModel(it) })
    }
}