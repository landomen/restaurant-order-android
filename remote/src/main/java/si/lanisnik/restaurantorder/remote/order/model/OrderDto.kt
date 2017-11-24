package si.lanisnik.restaurantorder.remote.order.model

import si.lanisnik.restaurantorder.remote.address.model.AddressDto
import si.lanisnik.restaurantorder.remote.order.model.create.SelectedMenuItemDto
import si.lanisnik.restaurantorder.remote.order.model.status.OrderStatusDto

/**
 * Created by Domen Lanišnik on 13/09/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderDto(
        val id: Int,
        val createdAt: Long,
        val total: Double,
        val customerComment: String? = null,
        val address: AddressDto,
        val menuItems: List<SelectedMenuItemDto> = listOf(),
        val orderChanges: List<OrderStatusDto> = listOf())