package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.cache.model.validity.CacheValidity
import si.lanisnik.restaurantorder.cache.model.validity.CachedDataType
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryCacheImpl @Inject constructor(private val mapper: FoodCategoryCacheMapper,
                                                private val database: RestaurantOrderDatabase) : FoodCategoryCache {

    override fun clearCategories(): Completable = Completable.defer {
        database.foodCategoryDao().clearCategories()
        Completable.complete()
    }

    override fun getCategories(): Flowable<List<FoodCategoryEntity>> = Flowable.just(database.foodCategoryDao()
            .getCategories())
            .map { it.map { mapper.mapFromCached(it) } }

    override fun saveCategories(categories: List<FoodCategoryEntity>): Completable = Completable.defer {
        categories.forEach {
            database.foodCategoryDao().insertCategory(mapper.mapToCached(it))
        }
        Completable.complete()
    }

    override fun isCached(): Single<Boolean> = Single.defer { Single.just(database.foodCategoryDao().getCategories().isNotEmpty())}

    override fun setLastCacheTime(time: Long) {
        database.validityDao().insertValidity(CacheValidity(CachedDataType.FOOD_CATEGORY, time))
    }

    override fun isValid(): Boolean = System.currentTimeMillis() < database.validityDao().getValidity(CachedDataType.FOOD_CATEGORY) + CacheConstants.EXPIRATION_TIME
}