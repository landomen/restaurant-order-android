package si.lanisnik.restaurantorder

import android.app.Activity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import io.reactivex.schedulers.Schedulers
import si.lanisnik.restaurantorder.data.entity.menuitems.AllMenuItemsResponse
import si.lanisnik.restaurantorder.data.net.RestApiClient
import si.lanisnik.restaurantorder.data.net.apis.RestApiMenuItems
import javax.inject.Inject

class SplashActivity : Activity() {

    @Inject
    private lateinit var menuItemsApi: RestApiMenuItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        (application as RestaurantOrderApp).networkComponent

        val allMenuItems = menuItemsApi.getAllMenuItems()
        allMenuItems.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t1, t2 ->

                })
    }
}
