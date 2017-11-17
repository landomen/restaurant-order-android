package si.lanisnik.restaurantorder.data.repository.order

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity

/**
 * Created by Domen Lanišnik on 11/11/2017.
 * domen.lanisnik@gmail.com
 */
interface OrderCache {

    fun clearShoppingCart(): Completable
    fun addItemToShoppingCart(item: SelectedMenuItemEntity): Completable
    fun removeItemFromShoppingCart(id: Long): Completable
    fun loadShoppingCart(): Single<List<SelectedMenuItemEntity>>

}