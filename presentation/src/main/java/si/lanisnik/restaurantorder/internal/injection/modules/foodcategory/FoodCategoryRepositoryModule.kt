package si.lanisnik.restaurantorder.internal.injection.modules.foodcategory

import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.FoodCategoryCacheImpl
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.foodcategory.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.data.FoodCategoryDataRepository
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryRemote
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.foodcategory.FoodCategoryRemoteImpl
import si.lanisnik.restaurantorder.remote.foodcategory.mapper.FoodCategoryRemoteMapper
import si.lanisnik.restaurantorder.remote.foodcategory.service.FoodCategoriesService

/**
 * Created by Domen Lanišnik on 05/10/2017.
 * domen.lanisnik@gmail.com
 */
@Module
class FoodCategoryRepositoryModule {

    @Provides
    @PerApplication
    fun provideFoodCategoryRepository(foodCategoryRepository: FoodCategoryDataRepository): FoodCategoryRepository = foodCategoryRepository

    @Provides
    @PerApplication
    fun provideFoodCategoryCache(database: RestaurantOrderDatabase,
                                 mapper: FoodCategoryCacheMapper,
                                 simpleStorage: SimpleStorage): FoodCategoryCache =
            FoodCategoryCacheImpl(mapper, database, simpleStorage)

    @Provides
    @PerApplication
    fun provideFoodCategoryRemote(service: FoodCategoriesService,
                                  mapper: FoodCategoryRemoteMapper): FoodCategoryRemote =
            FoodCategoryRemoteImpl(mapper, service)

    @Provides
    @PerApplication
    fun provideFoodCategoryService(serviceFactory: RestaurantOrderServiceFactory): FoodCategoriesService =
            serviceFactory.makeService(FoodCategoriesService::class.java)
}