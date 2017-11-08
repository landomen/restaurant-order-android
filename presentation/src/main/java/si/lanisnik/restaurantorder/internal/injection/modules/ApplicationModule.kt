package si.lanisnik.restaurantorder.internal.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import si.lanisnik.restaurantorder.BuildConfig
import si.lanisnik.restaurantorder.cache.component.AuthorizationComponentImpl
import si.lanisnik.restaurantorder.cache.preferences.SimpleStorage
import si.lanisnik.restaurantorder.data.component.AuthorizationComponent
import si.lanisnik.restaurantorder.data.executor.JobThread
import si.lanisnik.restaurantorder.domain.executor.JobExecutionThread
import si.lanisnik.restaurantorder.domain.executor.PostExecutionThread
import si.lanisnik.restaurantorder.internal.execution.MainThread
import si.lanisnik.restaurantorder.internal.injection.modules.address.AddressRepositoryModule
import si.lanisnik.restaurantorder.internal.injection.modules.customer.CustomerRepositoryModule
import si.lanisnik.restaurantorder.internal.injection.modules.foodcategory.FoodCategoryRepositoryModule
import si.lanisnik.restaurantorder.internal.injection.modules.menuitem.MenuItemRepositoryModule
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import si.lanisnik.restaurantorder.remote.RestaurantOrderServiceFactory
import si.lanisnik.restaurantorder.remote.interceptor.AuthenticationInterceptor

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = arrayOf(
        FoodCategoryRepositoryModule::class,
        MenuItemRepositoryModule::class,
        CustomerRepositoryModule::class,
        AddressRepositoryModule::class
))
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
    fun provideSimpleStorage(context: Context): SimpleStorage = SimpleStorage(context)

    @Provides
    @PerApplication
    fun provideAuthorizationComponent(simpleStorage: SimpleStorage): AuthorizationComponent =
            AuthorizationComponentImpl(simpleStorage)

    @Provides
    @PerApplication
    fun provideServiceFactory(authenticationInterceptor: AuthenticationInterceptor): RestaurantOrderServiceFactory =
            RestaurantOrderServiceFactory(BuildConfig.SERVER_BASE_URL, BuildConfig.DEBUG, authenticationInterceptor)

    // endregion
}