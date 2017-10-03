package si.lanisnik.restaurantorder.data.repository.foodcategory

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity

/**
 * Interface defining methods for the data operations related to Food Categories.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface FoodCategoryDataStore {

    fun getCategories(): Flowable<List<FoodCategoryEntity>>

    fun saveCategories(categories: List<FoodCategoryEntity>): Completable

    fun clearCategories(): Completable

    fun isCached(): Single<Boolean>
}