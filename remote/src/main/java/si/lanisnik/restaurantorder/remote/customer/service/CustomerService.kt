package si.lanisnik.restaurantorder.remote.customer.service

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import si.lanisnik.restaurantorder.remote.NetConstants
import si.lanisnik.restaurantorder.remote.customer.model.*

/**
 * Created by Domen Lanišnik on 03/09/2017.
 * domen.lanisnik@gmail.com
 */
interface CustomerService {

    @POST("customer/login")
    fun login(@Body request: LoginRequest): Single<CustomerDto>

    @POST("customer/register")
    fun register(@Body request: RegisterRequest): Single<CustomerDto>

    @POST("customer/resetPassword")
    fun resetPassword(@Body request: ResetPasswordRequest): Completable

    @POST("customer/changePassword")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun changePassword(@Body request: ChangePasswordRequest): Completable

    @POST("customer/profile")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun updateProfile(@Body request: UpdateProfileRequest): Single<CustomerDto>

    @GET("customer/profile")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getProfile(): Single<CustomerDto>

}