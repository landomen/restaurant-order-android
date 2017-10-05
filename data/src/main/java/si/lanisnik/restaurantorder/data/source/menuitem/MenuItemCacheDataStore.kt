package si.lanisnik.restaurantorder.data.source.menuitem

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemCache
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemDataStore
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemCacheDataStore @Inject constructor(private val menuItemCache: MenuItemCache) : MenuItemDataStore {

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>> = menuItemCache.getMenuItems(categoryId)

    override fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable =
            menuItemCache.saveMenuItems(categoryId, menuItems)
                    .doOnComplete {
                        menuItemCache.setLastCacheTime(categoryId, System.currentTimeMillis())
                    }

    override fun clearMenuItems(categoryId: Int): Completable = menuItemCache.clearMenuItems(categoryId)

    override fun isCached(categoryId: Int): Single<Boolean> = menuItemCache.isCached(categoryId)
}