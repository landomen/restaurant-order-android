package si.lanisnik.restaurantorder.data.source.foodcategory

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import si.lanisnik.restaurantorder.data.entity.foodcategory.FoodCategoryEntity
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryDataStore
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryRemote
import javax.inject.Inject

/**
 * Implementation of the [FoodCategoryDataStore] interface to provide a means of communicating
 * with the remote data source
 */
class FoodCategoryRemoteDataStore @Inject constructor(private val foodCategoryRemote: FoodCategoryRemote) : FoodCategoryDataStore {

    override fun getCategories(): Flowable<List<FoodCategoryEntity>> = foodCategoryRemote.getCategories()

    override fun saveCategories(categories: List<FoodCategoryEntity>): Completable {
        throw UnsupportedOperationException()
    }

    override fun clearCategories(): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}