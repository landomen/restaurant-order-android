package si.lanisnik.restaurantorder.domain.model.order

import si.lanisnik.restaurantorder.domain.model.address.DeliveryAddress

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class Order(
        val id: Int,
        val total: Double,
        val customerComment: String? = null,
        val address: DeliveryAddress,
        val menuItems: List<SelectedMenuItem> = listOf(),
        val orderChanges: List<OrderStatus> = listOf()
)