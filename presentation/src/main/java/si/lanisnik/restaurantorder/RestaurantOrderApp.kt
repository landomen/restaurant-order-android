package si.lanisnik.restaurantorder

import android.app.Activity
import android.app.Application
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import si.lanisnik.restaurantorder.internal.injection.components.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 25/09/2017.
 * domen.lanisnik@gmail.com
 */
class RestaurantOrderApp : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        setupTimber()
        setupRealm()
        setupLeakCanary()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun setupRealm() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().
                deleteRealmIfMigrationNeeded().
                build()
        )
    }

    private fun setupLeakCanary() {
        LeakCanary.install(this)
    }
}