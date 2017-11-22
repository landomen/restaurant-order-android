package si.lanisnik.restaurantorder.ui.order.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.order.shoppingcart.model.ShoppingCartItemModel

/**
 * Created by Domen Lanišnik on 22/11/2017.
 * domen.lanisnik@gmail.com
 */
@Parcel(Parcel.Serialization.BEAN)
data class OrderModel @ParcelConstructor constructor(
        val id: Int,
        val total: Double,
        val customerComment: String? = null,
        val address: AddressModel,
        val menuItems: List<ShoppingCartItemModel> = listOf(),
        val orderChanges: List<OrderStatusModel> = listOf()
)