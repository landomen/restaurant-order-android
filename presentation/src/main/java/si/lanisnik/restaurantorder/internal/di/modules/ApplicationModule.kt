package si.lanisnik.restaurantorder.internal.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.FoodCategoryCacheImpl
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.mapper.FoodCategoryCacheMapper
import si.lanisnik.restaurantorder.data.FoodCategoryDataRepository
import si.lanisnik.restaurantorder.data.executor.JobThread
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryCache
import si.lanisnik.restaurantorder.data.repository.foodcategory.FoodCategoryRemote
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.domain.repository.FoodCategoryRepository
import si.lanisnik.restaurantorder.internal.execution.MainThread
import si.lanisnik.restaurantorder.remote.FoodCategoryRemoteImpl
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.mapper.FoodCategoryRemoteMapper
import si.lanisnik.restaurantorder.remote.service.CustomerService
import si.lanisnik.restaurantorder.remote.service.FoodCategoriesService
import si.lanisnik.restaurantorder.remote.service.MenuItemsService
import si.lanisnik.restaurantorder.remote.service.OrdersService
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
open class ApplicationModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideJobExecutionThread(jobThread: JobThread): JobExecutionThread = jobThread

    @Provides
    @Singleton
    fun providePostExecutionThread(mainThread: MainThread): PostExecutionThread = mainThread

    @Provides
    @Singleton
    fun provideRestaurantOrderDatabase(context: Context): RestaurantOrderDatabase = RestaurantOrderDatabase.getInstance(context.applicationContext)

    // region Repository

    @Provides
    @Singleton
    fun provideFoodCategoryRepository(foodCategoryRepository: FoodCategoryDataRepository): FoodCategoryRepository = foodCategoryRepository

    @Provides
    @Singleton
    fun provideFoodCategoryCache(database: RestaurantOrderDatabase, mapper: FoodCategoryCacheMapper): FoodCategoryCache = FoodCategoryCacheImpl(mapper, database)

    @Provides
    @Singleton
    fun provideFoodCategoryRemote(service: FoodCategoriesService, mapper: FoodCategoryRemoteMapper): FoodCategoryRemote = FoodCategoryRemoteImpl(mapper, service)

//    @Provides
//    @Singleton
//    fun provideMenuItemRepository(menuItemDataRepository: MenuItemDataRepository): MenuItemRepository = menuItemDataRepository

    // endregion

    // region Remote

    @Provides
    @Singleton
    fun provideFoodCategoryService(): FoodCategoriesService = RestaurantOrderServiceFactory.makeService(FoodCategoriesService::class.java)

    @Provides
    @Singleton
    fun provideCustomerService(): CustomerService = RestaurantOrderServiceFactory.makeService(CustomerService::class.java)

    @Provides
    @Singleton
    fun provideMenuItemsService(): MenuItemsService = RestaurantOrderServiceFactory.makeService(MenuItemsService::class.java)

    @Provides
    @Singleton
    fun provideOrdersService(): OrdersService = RestaurantOrderServiceFactory.makeService(OrdersService::class.java)

    // endregion
}