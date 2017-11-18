package si.lanisnik.restaurantorder.remote.order

import io.reactivex.Completable
import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity
import si.lanisnik.restaurantorder.data.repository.order.OrderRemote
import si.lanisnik.restaurantorder.remote.order.mapper.SelectedMenuItemRemoteMapper
import si.lanisnik.restaurantorder.remote.order.model.create.CreateOrderRequest
import si.lanisnik.restaurantorder.remote.order.service.OrdersService
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderRemoteImpl @Inject constructor(private val service: OrdersService,
                                          private val selectedMenuItemMapper: SelectedMenuItemRemoteMapper) : OrderRemote {

    override fun createOrder(addressId: Int, items: List<SelectedMenuItemEntity>, comment: String?): Completable {
        val createOrderRequest = CreateOrderRequest(addressId, comment, items.map { selectedMenuItemMapper.mapToRemote(it) })
        return service.createOrder(createOrderRequest)
    }

}