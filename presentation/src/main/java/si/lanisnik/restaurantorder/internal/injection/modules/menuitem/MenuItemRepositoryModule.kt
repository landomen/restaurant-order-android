package si.lanisnik.restaurantorder.internal.injection.modules.menuitem

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.MenuItemCacheImpl
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.menuitem.MenuItemCacheMapper
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.data.MenuItemDataRepository
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemCache
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemRemote
import si.lanisnik.restaurantorder.domain.repository.MenuItemRepository
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.menuitem.MenuItemRemoteImpl
import si.lanisnik.restaurantorder.remote.menuitem.mapper.MenuItemRemoteMapper
import si.lanisnik.restaurantorder.remote.menuitem.service.MenuItemsService

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class MenuItemRepositoryModule {

    @Provides
    @PerApplication
    fun provideMenuItemRepository(menuItemRepository: MenuItemDataRepository): MenuItemRepository = menuItemRepository

    @Provides
    @PerApplication
    fun provideMenuItemCache(database: RestaurantOrderDatabase,
                             mapper: MenuItemCacheMapper,
                             simpleStorage: SimpleStorage): MenuItemCache =
            MenuItemCacheImpl(mapper, database, simpleStorage)

    @Provides
    @PerApplication
    fun provideMenuItemRemote(service: MenuItemsService, mapper: MenuItemRemoteMapper): MenuItemRemote =
            MenuItemRemoteImpl(service, mapper)

    @Provides
    @PerApplication
    fun provideMenuItemsService(): MenuItemsService = RestaurantOrderServiceFactory.makeService(MenuItemsService::class.java)
}