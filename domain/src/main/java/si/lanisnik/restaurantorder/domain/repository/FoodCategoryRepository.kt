package si.lanisnik.restaurantorder.domain.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory

/**
 * Interface defining methods for how the data layer can pass FoodCategory data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface FoodCategoryRepository {

    fun getCategories(): Flowable<List<FoodCategory>>

    fun saveCategories(categories: List<FoodCategory>): Completable

}