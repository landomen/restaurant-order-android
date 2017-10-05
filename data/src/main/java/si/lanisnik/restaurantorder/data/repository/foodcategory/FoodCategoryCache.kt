package si.lanisnik.restaurantorder.data.repository.foodcategory

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity

/**
 * Interface defining methods for the caching of Food categories. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface FoodCategoryCache {

    fun clearCategories(): Completable

    fun getCategories(): Flowable<List<FoodCategoryEntity>>

    fun saveCategories(categories: List<FoodCategoryEntity>): Completable

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(time: Long)

    fun isExpired(): Boolean
}