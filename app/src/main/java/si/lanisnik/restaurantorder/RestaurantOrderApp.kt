package si.lanisnik.restaurantorder

import android.app.Application
import si.lanisnik.restaurantorder.data.di.component.DaggerNetworkComponent
import si.lanisnik.restaurantorder.data.di.component.NetworkComponent
import si.lanisnik.restaurantorder.data.di.module.NetModule

/**
 * Created by Domen Lanišnik on 14/09/2017.
 * domen.lanisnik@gmail.com
 */
class RestaurantOrderApp : Application() {

    val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.builder()
                .netModule(NetModule(BuildConfig.SERVER_BASE_URL))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }

//    protected fun initDagger(){
//        return DaggerNetworkComponent.builder()
//                .netModule()
//    }
}