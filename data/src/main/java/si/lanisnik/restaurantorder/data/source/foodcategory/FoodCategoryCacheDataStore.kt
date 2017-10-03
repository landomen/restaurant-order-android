package si.lanisnik.restaurantorder.data.source.foodcategory

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryDataStore
import javax.inject.Inject

/**
 * Implementation of the [FoodCategoryDataStore] interface to provide a means of communicating
 * with the local data source
 */
class FoodCategoryCacheDataStore @Inject constructor(private val foodCategoryCache: FoodCategoryCache) :
        FoodCategoryDataStore {

    override fun clearCategories(): Completable = foodCategoryCache.clearCategories()

    override fun isCached(): Single<Boolean> = foodCategoryCache.isCached()

    override fun getCategories(): Flowable<List<FoodCategoryEntity>> = foodCategoryCache.getCategories()

    override fun saveCategories(categories: List<FoodCategoryEntity>): Completable =
            foodCategoryCache.saveCategories(categories)
                    .doOnComplete {
                        foodCategoryCache.setLastCacheTime(System.currentTimeMillis())
                    }
}