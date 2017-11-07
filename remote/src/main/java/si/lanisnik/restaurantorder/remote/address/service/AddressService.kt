package si.lanisnik.restaurantorder.remote.address.service

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import si.lanisnik.restaurantorder.remote.NetConstants
import si.lanisnik.restaurantorder.remote.address.model.AddressDto
import si.lanisnik.restaurantorder.remote.address.model.AddressRequest
import si.lanisnik.restaurantorder.remote.address.model.AddressesResponseDto

/**
 * Created by Domen Lanišnik on 06/11/2017.
 * domen.lanisnik@gmail.com
 */
interface AddressService {

    @GET("address")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getAddresses(): Single<AddressesResponseDto>

    @PUT("address")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun addAddress(@Body request: AddressRequest): Single<AddressDto>

    @POST("address/{id}")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun updateAddress(@Path("id") id: Int, @Body request: AddressRequest): Single<AddressDto>

    @POST("address({id}/select")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun selectAddress(@Path("id") id: Int): Completable

    @DELETE("address/{id}")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun deleteAddress(@Path("id") id: Int): Completable

}