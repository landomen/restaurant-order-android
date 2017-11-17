package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.mapper.order.SelectedMenuItemCacheMapper
import si.lanisnik.restaurantorder.cache.model.order.shoppingcart.CachedSelectedMenuItem
import si.lanisnik.restaurantorder.cache.model.order.shoppingcart.CachedShoppingCart
import si.lanisnik.restaurantorder.cache.util.getRealm
import si.lanisnik.restaurantorder.cache.util.transaction
import si.lanisnik.restaurantorder.cache.util.where
import si.lanisnik.restaurantorder.data.entity.orders.SelectedMenuItemEntity
import si.lanisnik.restaurantorder.data.repository.order.OrderCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderCacheImpl @Inject constructor(private val mapper: SelectedMenuItemCacheMapper) : OrderCache {

    override fun clearShoppingCart(): Completable {
        return Completable.defer {
            getRealm().transaction {
                it.where<CachedShoppingCart>().findAll().deleteAllFromRealm()
                it.where<CachedSelectedMenuItem>().findAll().deleteAllFromRealm()
            }
            Completable.complete()
        }
    }

    override fun addItemToShoppingCart(item: SelectedMenuItemEntity): Completable {
        return Completable.defer {
            getRealm().transaction {
                val shoppingCart = it.where<CachedShoppingCart>().findFirst() ?: it.copyToRealm(CachedShoppingCart())
                shoppingCart.selectedMenuItems.add(mapper.mapToCached(item))
            }
            Completable.complete()
        }
    }

    override fun removeItemFromShoppingCart(id: Long): Completable {
        return Completable.defer {
            getRealm().transaction {
                val shoppingCart = it.where<CachedShoppingCart>().findFirst()!!
                shoppingCart.selectedMenuItems
                        .where()
                        .equalTo("id", id)
                        .findFirst()
                        ?.deleteFromRealm()
            }
            Completable.complete()
        }
    }

    override fun loadShoppingCart(): Single<List<SelectedMenuItemEntity>> {
        return Single.defer {
            Single.just(getRealm().use {
                val shoppingCart = it.where<CachedShoppingCart>().findFirst()
                shoppingCart?.selectedMenuItems?.map { mapper.mapFromCached(it) } ?: listOf()
            })
        }
    }
}