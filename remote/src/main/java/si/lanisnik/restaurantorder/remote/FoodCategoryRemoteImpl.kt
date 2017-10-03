package si.lanisnik.restaurantorder.remote

import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryRemote
import si.lanisnik.restaurantorder.remote.mapper.FoodCategoryRemoteMapper
import si.lanisnik.restaurantorder.remote.service.FoodCategoriesService
import javax.inject.Inject

/**
 * Remote implementation for retrieving Food categores. This class implements the
 * [FoodCategoryRemote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */
class FoodCategoryRemoteImpl @Inject constructor(private val mapper: FoodCategoryRemoteMapper,
                                                 private val service: FoodCategoriesService) : FoodCategoryRemote {

    override fun getCategories(): Flowable<List<FoodCategoryEntity>> =
            service.getFoodCategories()
                    .map { it.categories }
                    .map { categories ->
                        categories.map { mapper.mapFromRemote(it) }
                    }
}