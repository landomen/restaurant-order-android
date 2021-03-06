package si.lanisnik.restaurantorder.ui.order.model

import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
data class OrderModel(val id: Int,
                      val createdAt: Long,
                      val total: Double,
                      val customerComment: String? = null,
                      val address: AddressModel,
                      val menuItems: List<ShoppingCartItemModel> = listOf(),
                      val orderChanges: List<OrderStatusModel> = listOf()){
}