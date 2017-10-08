package si.lanisnik.restaurantorder.data.source.foodcategory

import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryDataStore
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/10/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryDataStoreFactory @Inject constructor(
        private val foodCategoryCache: FoodCategoryCache,
        private val foodCategoryCacheDataStore: FoodCategoryCacheDataStore,
        private val foodCategoryRemoteDataStore: FoodCategoryRemoteDataStore) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    fun retrieveDataStore(isCached: Boolean): FoodCategoryDataStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    fun retrieveCacheDataStore(): FoodCategoryDataStore = foodCategoryCacheDataStore

    /**
     * Return an instance of the Cache Data Store
     */
    fun retrieveRemoteDataStore(): FoodCategoryDataStore = foodCategoryRemoteDataStore

}