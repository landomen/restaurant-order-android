package si.lanisnik.restaurantorder.data.repository.menuitem

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity

/**
 * Interface defining methods for the caching of Menu items. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface MenuItemCache {

    fun clearMenuItems(categoryId: Int): Completable

    fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>>

    fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable

    fun isCached(categoryId: Int): Single<Boolean>

    fun setLastCacheTime(categoryId: Int, time: Long)

}