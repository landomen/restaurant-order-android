package si.lanisnik.restaurantorder.data.repository.order

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.orders.OrderEntity
import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
interface OrderRemote {

    fun createOrder(addressId: Int, items: List<SelectedMenuItemEntity>, comment: String?): Completable

    fun getOrdersHistory(): Single<List<OrderEntity>>
}