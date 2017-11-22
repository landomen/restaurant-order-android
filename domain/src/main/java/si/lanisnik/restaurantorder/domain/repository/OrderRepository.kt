package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.domain.model.order.Order
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem

/**
 * Created by Domen Lanišnik on 22/09/2017.
 * domen.lanisnik@gmail.com
 */
interface OrderRepository {

    fun clearShoppingCart(): Completable
    fun addItemToShoppingCart(item: SelectedMenuItem): Completable
    fun removeItemFromShoppingCart(selectedItemId: Long): Completable
    fun loadShoppingCart(): Single<List<SelectedMenuItem>>

    fun createOrder(addressId: Int, items: List<SelectedMenuItem>, comment: String?): Completable

    fun getOrdersHistory(): Single<List<Order>>

}