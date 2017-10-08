package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.foodcategory.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.util.isCacheExpired
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryCacheImpl @Inject constructor(private val mapper: FoodCategoryCacheMapper,
                                                private val database: RestaurantOrderDatabase,
                                                private val simpleStorage: SimpleStorage) : FoodCategoryCache {

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

    override fun isCached(): Single<Boolean> = Single.defer { Single.just(database.foodCategoryDao().getCategories().isNotEmpty() && !isCacheExpired(simpleStorage.getLong(LongKey.LAST_CACHE_TIME_FOOD_CATEGORY))) }

    override fun setLastCacheTime(time: Long) {
        simpleStorage.putLong(LongKey.LAST_CACHE_TIME_FOOD_CATEGORY, time)
    }
}