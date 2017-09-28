package si.lanisnik.restaurantorder.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import si.lanisnik.restaurantorder.RestaurantOrderApp
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 17/09/2017.
 * domen.lanisnik@gmail.com
 */
open class BaseActivity : AppCompatActivity(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        (application as RestaurantOrderApp).applicationComponent.inject(this)
    }
}