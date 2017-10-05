package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.menuitem.MenuItemCacheMapper
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.util.isCacheExpired
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemCacheImpl @Inject constructor(private val mapper: MenuItemCacheMapper,
                                            private val database: RestaurantOrderDatabase,
                                            private val simpleStorage: SimpleStorage) : MenuItemCache {

    override fun clearMenuItems(categoryId: Int): Completable = Completable.defer {
        database.menuItemDao().clearMenuItems(categoryId)
        Completable.complete()
    }

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>> = Flowable.just(database.menuItemDao()
            .getAllMenuItems(categoryId))
            .map { it.map { mapper.mapFromCached(it) } }

    override fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable = Completable.defer {
        menuItems.forEach {
            database.menuItemDao().insertMenuItem(mapper.mapToCached(it))
        }
        Completable.complete()
    }

    override fun isCached(categoryId: Int): Single<Boolean> = Single.defer {
        Single.just(database.menuItemDao().getAllMenuItems(categoryId).isNotEmpty())
    }

    override fun setLastCacheTime(categoryId: Int, time: Long) {
        simpleStorage.putLong(LongKey.LAST_CACHE_TIME_MENU_ITEM, time, categoryId.toString())
    }

    override fun isExpired(categoryId: Int): Boolean = isCacheExpired(simpleStorage.getLong(LongKey.LAST_CACHE_TIME_MENU_ITEM, suffix = categoryId.toString()))
}