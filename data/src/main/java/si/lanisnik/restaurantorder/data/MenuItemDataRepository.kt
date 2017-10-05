package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.mapper.MenuItemEntityMapper
import si.lanisnik.restaurantorder.data.source.menuitem.MenuItemDataStoreFactory
import si.lanisnik.restaurantorder.domain.model.menuitem.MenuItem
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDataRepository @Inject constructor(private val factory: MenuItemDataStoreFactory,
                                                 private val mapper: MenuItemEntityMapper) : MenuItemRepository {

    override fun clearMenuItems(categoryId: Int): Completable =
            factory.retrieveCacheDataStore().clearMenuItems(categoryId)

    override fun getMenuItems(categoryId: Int): Flowable<List<MenuItem>> {
        return factory.retrieveCacheDataStore().isCached(categoryId)
                .flatMapPublisher {
                    factory.retrieveDataStore(categoryId, it).getMenuItems(categoryId)
                }
                .flatMap {
                    Flowable.just(it.map { mapper.mapFromEntity(it) })
                }
                .flatMap {
                    saveMenuItems(categoryId, it).toSingle { it }.toFlowable()
                }
    }

    override fun saveMenuItems(categoryId: Int, menuItems: List<MenuItem>): Completable =
            factory.retrieveCacheDataStore().saveMenuItems(categoryId, menuItems.map { mapper.mapToEntity(it) })
}