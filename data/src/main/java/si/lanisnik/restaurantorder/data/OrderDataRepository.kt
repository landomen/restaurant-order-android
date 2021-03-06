package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.mapper.OrderEntityMapper
import si.lanisnik.restaurantorder.data.mapper.OrderHistoryEntityMapper
import si.lanisnik.restaurantorder.data.mapper.SelectedMenuItemEntityMapper
import si.lanisnik.restaurantorder.data.repository.order.OrderCache
import si.lanisnik.restaurantorder.data.repository.order.OrderRemote
import si.lanisnik.restaurantorder.domain.model.order.Order
import si.lanisnik.restaurantorder.domain.model.order.OrderHistory
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem
import si.lanisnik.restaurantorder.domain.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderDataRepository @Inject constructor(private val cache: OrderCache,
                                              private val remote: OrderRemote,
                                              private val selectedMenuItemMapper: SelectedMenuItemEntityMapper,
                                              private val orderMapper: OrderEntityMapper,
                                              private val orderHistoryMapper: OrderHistoryEntityMapper) : OrderRepository {

    override fun clearShoppingCart(): Completable = cache.clearShoppingCart()

    override fun addItemToShoppingCart(item: SelectedMenuItem): Completable =
            cache.addItemToShoppingCart(selectedMenuItemMapper.mapToEntity(item))

    override fun removeItemFromShoppingCart(selectedItemId: Long): Completable =
            cache.removeItemFromShoppingCart(selectedItemId)

    override fun loadShoppingCart(): Single<List<SelectedMenuItem>> {
        return cache.loadShoppingCart()
                .map {
                    it.map { selectedMenuItemMapper.mapFromEntity(it) }
                }
    }

    override fun createOrder(addressId: Int, items: List<SelectedMenuItem>, comment: String?): Completable {
        return remote.createOrder(addressId, items.map { selectedMenuItemMapper.mapToEntity(it) }, comment)
    }

    override fun getOrdersHistory(): Single<List<OrderHistory>> {
        return remote.getOrdersHistory()
                .map {
                    it.map {
                        orderHistoryMapper.mapFromEntity(it)
                    }
                }
    }

    override fun getOrderHistoryDetails(id: Int): Single<Order> {
        return remote.getOrderHistoryDetails(id)
                .map { orderMapper.mapFromEntity(it) }
    }


}