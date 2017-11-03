package si.lanisnik.restaurantorder.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.cache.mapper.foodcategory.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.cache.model.foodcategory.CachedFoodCategory
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.cache.preferences.keys.LongKey
import si.lanisnik.restaurantorder.cache.util.*
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 02/10/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryCacheImpl @Inject constructor(private val mapper: FoodCategoryCacheMapper,
                                                private val simpleStorage: SimpleStorage) : FoodCategoryCache {

    override fun clearCategories(): Completable = Completable.defer {
        getRealm().transaction {
            it.where<CachedFoodCategory>().findAll().deleteAllFromRealm()
        }
        Completable.complete()
    }

    override fun getCategories(): Flowable<List<FoodCategoryEntity>> {
        return Flowable.just(getRealm().use {
            val categories = it.where<CachedFoodCategory>().findAll()
            categories.map { mapper.mapFromCached(it) }
        })
    }

    override fun saveCategories(categories: List<FoodCategoryEntity>): Completable = Completable.defer {
        getRealm().transaction { realm ->
            categories.forEach {
                realm.insertOrUpdate(mapper.mapToCached(it))
            }
        }
        Completable.complete()
    }

    override fun isCached(): Single<Boolean> {
        return Single.defer {
            Single.just(
                    getRealm().contains<CachedFoodCategory>() &&
                            !isCacheExpired(simpleStorage.getLong(LongKey.LAST_CACHE_TIME_FOOD_CATEGORY)))
        }
    }

    override fun setLastCacheTime(time: Long) {
        simpleStorage.putLong(LongKey.LAST_CACHE_TIME_FOOD_CATEGORY, time)
    }
}