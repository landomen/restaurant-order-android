package si.lanisnik.restaurantorder.remote.customer.service

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.remote.NetConstants
import si.lanisnik.restaurantorder.remote.base.model.SimpleIdResponse
import si.lanisnik.restaurantorder.remote.customer.model.CustomerDto
import si.lanisnik.restaurantorder.remote.customer.model.LoginRequest
import si.lanisnik.restaurantorder.remote.customer.model.RegisterRequest

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerService {

    @POST("customer/login")
    fun login(@Body request: LoginRequest): Single<CustomerDto>

    @POST("customer/register")
    fun register(@Body request: RegisterRequest): Single<SimpleIdResponse>

    @POST("customer/profile")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getProfile(): Single<CustomerDto>

}