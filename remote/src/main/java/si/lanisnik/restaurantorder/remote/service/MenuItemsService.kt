package si.lanisnik.restaurantorder.remote.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import si.lanisnik.restaurantorder.remote.model.menuitem.MenuItemsResponse

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
interface MenuItemsService {

    @GET("menu-items/category")
    fun getMenuItems(@Query("id") categoryId: Int): Single<MenuItemsResponse>

}