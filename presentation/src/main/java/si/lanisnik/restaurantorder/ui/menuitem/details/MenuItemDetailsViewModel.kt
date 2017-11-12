package si.lanisnik.restaurantorder.ui.menuitem.details

import si.lanisnik.restaurantorder.domain.model.order.ShoppingCart
import si.lanisnik.restaurantorder.ui.base.viewmodels.ShoppingCartViewModel
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModel @Inject constructor(shoppingCart: ShoppingCart) : ShoppingCartViewModel(shoppingCart) {

    private lateinit var menuItem: MenuItemModel

    fun initialize(menuItem: MenuItemModel) {
        this.menuItem = menuItem
        initShoppingCart()
    }

    fun getTitle(): String = menuItem.title

    fun getMenuItemDescription(): String = menuItem.description

    fun getMenuItemImageUrl(): String? = menuItem.image

    fun getMenuItemPrice(): String = menuItem.getFormattedPrice()

    fun addToShoppingCart() {
        shoppingCart.addItem(menuItem.id)
    }

    fun updateShoppingCart(count: Int, comment: String) {
        shoppingCart.updateItem(menuItem.id, count, comment)
    }

    fun deleteFromShoppingCart() {
        shoppingCart.deleteItem(menuItem.id)
    }

}