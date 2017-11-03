package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmResults
import si.lanisnik.restaurantorder.cache.mapper.menuitem.MenuItemCacheMapper
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.cache.model.menuitem.CachedMenuItem
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.util.*
import si.lanisnik.restaurantorder.data.entity.menuitem.MenuItemEntity
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemCacheImpl @Inject constructor(private val menuItemMapper: MenuItemCacheMapper,
                                            private val simpleStorage: SimpleStorage) : MenuItemCache {

    override fun clearMenuItems(categoryId: Int): Completable = Completable.defer {
        getRealm().transaction {
            findMenuItems(it, categoryId).deleteAllFromRealm()
        }
        Completable.complete()
    }

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItemEntity>> = Flowable.just(
            getRealm().use {
                val menuItems = findMenuItems(it, categoryId)
                menuItems.map { menuItemMapper.mapFromCached(it) }
            }
    )

    override fun saveMenuItems(categoryId: Int, menuItems: List<MenuItemEntity>): Completable = Completable.defer {
        getRealm().transaction { realm ->
            findMenuItems(realm, categoryId).deleteAllFromRealm()
            menuItems.forEach {
                realm.insertOrUpdate(menuItemMapper.mapToCached(it))
            }
        }
        Completable.complete()
    }

    override fun isCached(categoryId: Int): Single<Boolean> = Single.defer {
        Single.just(getRealm().contains<CachedFoodCategory>() &&
                !isCacheExpired(simpleStorage.getLong(LongKey.LAST_CACHE_TIME_MENU_ITEM, suffix = categoryId.toString()))
        )
    }

    override fun setLastCacheTime(categoryId: Int, time: Long) {
        simpleStorage.putLong(LongKey.LAST_CACHE_TIME_MENU_ITEM, time, categoryId.toString())
    }

    private fun findMenuItems(realm: Realm, categoryId: Int): RealmResults<CachedMenuItem> {
        return realm.where<CachedMenuItem>()
                .equalTo(RealmConstants.FIELD_CATEGORY_ID, categoryId)
                .findAll()
    }

}