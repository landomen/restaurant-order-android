package si.lanisnik.restaurantorder.data.repository.foodcategory

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity

/**
 * Interface defining methods for the retrieval of Food categories from Rest API. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */
interface FoodCategoryRemote {

    fun getCategories(): Flowable<List<FoodCategoryEntity>>
}