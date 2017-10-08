package si.lanisnik.restaurantorder.data.source.menuitem

import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemCache
import si.lanisnik.restaurantorder.data.repository.menuitem.MenuItemDataStore
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDataStoreFactory @Inject constructor(
        private val menuItemCache: MenuItemCache,
        private val menuItemCacheDataStore: MenuItemCacheDataStore,
        private val menuitemRemoteDataStore: MenuitemRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    fun retrieveDataStore(isCached: Boolean): MenuItemDataStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    fun retrieveCacheDataStore(): MenuItemDataStore = menuItemCacheDataStore

    /**
     * Return an instance of the Cache Data Store
     */
    fun retrieveRemoteDataStore(): MenuItemDataStore = menuitemRemoteDataStore


}