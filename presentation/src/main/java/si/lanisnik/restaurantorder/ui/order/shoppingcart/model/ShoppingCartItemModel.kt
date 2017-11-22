package si.lanisnik.restaurantorder.ui.order.shoppingcart.model

import org.parceler.Parcel
import org.parceler.ParcelConstructor
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
@Parcel(Parcel.Serialization.BEAN)
data class ShoppingCartItemModel @ParcelConstructor constructor(var id: Long,
                                                                val menuItem: MenuItemModel,
                                                                val comment: String?)