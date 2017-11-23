package si.lanisnik.restaurantorder.remote.order.service

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import si.lanisnik.restaurantorder.remote.NetConstants
import si.lanisnik.restaurantorder.remote.order.model.OrderDto
import si.lanisnik.restaurantorder.remote.order.model.OrderHistoryDto
import si.lanisnik.restaurantorder.remote.order.model.create.CreateOrderRequest

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
interface OrdersService {

    @POST("order/create")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun createOrder(@Body request: CreateOrderRequest): Completable

    @GET("order/history")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getOrderHistory(): Single<List<OrderHistoryDto>>

    @GET("order/history/{id}")
    @Headers(NetConstants.HEADER_AUTHORIZATION_PLACEHOLDER)
    fun getOrderHistoryDetails(@Path("id") id: Int): Single<OrderDto>

}