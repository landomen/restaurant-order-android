package si.lanisnik.restaurantorder.data.net

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.data.entity.customer.RegisterRequest
import si.lanisnik.restaurantorder.data.entity.menuitems.AllMenuItemsResponse

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
interface RestApiService {

    @POST("customer/login")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun login(): Single<ResponseBody>

    @POST("customer/register")
    fun register(@Body request: RegisterRequest): Single<ResponseBody>

    @GET("menu-items")
    fun getAllMenuItems(): Single<AllMenuItemsResponse>
}