package si.lanisnik.restaurantorder.data.entity.orders

import si.lanisnik.restaurantorder.data.entity.address.AddressEntity

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderEntity(
        val id: Int,
        val total: Double,
        val customerComment: String? = null,
        val address: AddressEntity,
        val menuItems: List<SelectedMenuItemEntity> = listOf(),
        val orderChanges: List<OrderStatusEntity> = listOf()
)