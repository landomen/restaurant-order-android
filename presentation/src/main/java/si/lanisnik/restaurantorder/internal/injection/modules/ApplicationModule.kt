package si.lanisnik.restaurantorder.internal.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.cache.db.RestaurantOrderDatabase
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.data.executor.JobThread
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.internal.execution.MainThread
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = arrayOf(FoodCategoryRepositoryModule::class, MenuItemRepositoryModule::class))
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun provideJobExecutionThread(jobThread: JobThread): JobExecutionThread = jobThread

    @Provides
    @PerApplication
    fun providePostExecutionThread(mainThread: MainThread): PostExecutionThread = mainThread

    @Provides
    @PerApplication
    fun provideRestaurantOrderDatabase(context: Context): RestaurantOrderDatabase = RestaurantOrderDatabase.getInstance(context.applicationContext)

    @Provides
    @PerApplication
    fun provideSimpleStorage(context: Context): SimpleStorage = SimpleStorage(context)

//    @Provides
//    @PerApplication
//    fun provideCustomerService(): CustomerService = RestaurantOrderServiceFactory.makeService(CustomerService::class.java)
//
//    @Provides
//    @PerApplication
//    fun provideOrdersService(): OrdersService = RestaurantOrderServiceFactory.makeService(OrdersService::class.java)

    // endregion
}