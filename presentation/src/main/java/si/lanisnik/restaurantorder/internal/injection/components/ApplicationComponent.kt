package si.lanisnik.restaurantorder.internal.injection.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import si.lanisnik.restaurantorder.RestaurantOrderApp
import si.lanisnik.restaurantorder.internal.injection.modules.ActivityBindingModule
import si.lanisnik.restaurantorder.internal.injection.modules.ApplicationModule
import si.lanisnik.restaurantorder.internal.injection.modules.FoodCategoryRepositoryModule
import si.lanisnik.restaurantorder.internal.injection.scopes.PerApplication
import javax.inject.Singleton


/**
 * A component whose lifetime is the life of the application.
 */
@PerApplication  // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = arrayOf(ApplicationModule::class, FoodCategoryRepositoryModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
interface ApplicationComponent {
    fun inject(application: RestaurantOrderApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

//    // region Exposed to sub-graphs
//
//    fun context(): Context
//    fun jobExecutionThread(): JobExecutionThread
//    fun postExecutionThread(): PostExecutionThread
//
//    // region Repositories
//
//    fun foodCategoryRepository(): FoodCategoryRepository
//
//    // endregion Repositories
//
//    // endregion
}