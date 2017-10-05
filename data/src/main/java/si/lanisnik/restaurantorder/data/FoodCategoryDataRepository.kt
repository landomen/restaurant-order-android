package si.lanisnik.restaurantorder.data

import io.reactivex.Completable
import io.reactivex.Flowable
import si.lanisnik.restaurantorder.data.mapper.FoodCategoryEntityMapper
import si.lanisnik.restaurantorder.data.source.foodcategory.FoodCategoryDataStoreFactory
import si.lanisnik.restaurantorder.domain.model.foodcategory.FoodCategory
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 29/09/2017.
 * domen.lanisnik@gmail.com
 */
class FoodCategoryDataRepository @Inject constructor(private val factory: FoodCategoryDataStoreFactory,
                                                     private val mapper: FoodCategoryEntityMapper) : FoodCategoryRepository {
    override fun clearCategories(): Completable =
            factory.retrieveCacheDataStore().clearCategories()

    override fun getCategories(): Flowable<List<FoodCategory>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getCategories()
                }
                .flatMap {
                    Flowable.just(it.map { mapper.mapFromEntity(it) })
                }
                .flatMap {
                    saveCategories(it).toSingle { it }.toFlowable()
                }
    }

    override fun saveCategories(categories: List<FoodCategory>): Completable =
            factory.retrieveCacheDataStore().saveCategories(categories.map { mapper.mapToEntity(it) })

}