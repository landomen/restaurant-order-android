package si.lanisnik.restaurantorder

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import si.lanisnik.restaurantorder.internal.di.components.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 25/09/2017.
 * domen.lanisnik@gmail.com
 */
class RestaurantOrderApp : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

//    val applicationComponent: ApplicationComponent by lazy {
//        DaggerApplicationComponent.builder()
//                .applicationModule(ApplicationModule(this))
//                .build()
//    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}