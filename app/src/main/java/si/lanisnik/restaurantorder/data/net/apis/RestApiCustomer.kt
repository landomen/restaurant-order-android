package si.lanisnik.restaurantorder.data.net.apis

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.data.entity.customer.Customer
import si.lanisnik.restaurantorder.data.entity.customer.RegisterRequest
import si.lanisnik.restaurantorder.data.entity.general.SimpleIdResponse
import si.lanisnik.restaurantorder.data.net.NetConstants

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
interface RestApiCustomer {

    @POST("customer/login")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun login(): Single<Customer>

    @POST("customer/register")
    fun register(@Body request: RegisterRequest): Single<SimpleIdResponse>

    @POST("customer/profile")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getProfile(): Single<Customer>

}