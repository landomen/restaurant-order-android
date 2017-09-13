package si.lanisnik.restaurantorder.data.net.apis

import io.reactivex.Single
import retrofit2.http.GET
import si.lanisnik.restaurantorder.data.entity.menuitems.AllMenuItemsResponse

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
interface RestApiMenuItems {

    @GET("menu-items")
    fun getAllMenuItems(): Single<AllMenuItemsResponse>

}