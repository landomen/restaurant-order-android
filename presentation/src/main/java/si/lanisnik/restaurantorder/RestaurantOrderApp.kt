package si.lanisnik.restaurantorder

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import si.lanisnik.restaurantorder.internal.di.components.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 25/09/2017.
 * domen.lanisnik@gmail.com
 */
class RestaurantOrderApp : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        setupTimber()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}