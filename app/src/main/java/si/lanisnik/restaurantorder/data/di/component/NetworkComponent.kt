package si.lanisnik.restaurantorder.data.di.component

import android.app.Activity
import android.content.Context
import dagger.Component
import si.lanisnik.restaurantorder.SplashActivity
import si.lanisnik.restaurantorder.data.di.module.NetModule
import javax.inject.Singleton

/**
 * Created by Domen Lanišnik on 16/09/2017.
 * domen.lanisnik@gmail.com
 */
@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetworkComponent{
    fun inject(activity: Activity)
}