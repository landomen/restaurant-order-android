package si.lanisnik.restaurantorder.ui.menuitem.details

import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.domain.model.order.SelectedMenuItem
import si.lanisnik.restaurantorder.mapper.MenuItemMapper
import si.lanisnik.restaurantorder.ui.base.viewmodels.ShoppingCartViewModel
import si.lanisnik.restaurantorder.ui.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModel @Inject constructor(shoppingCart: ShoppingCart,
                                                   private val menuItemMapper: MenuItemMapper) : ShoppingCartViewModel(shoppingCart) {

    private lateinit var menuItem: MenuItemModel

    fun initialize(menuItem: MenuItemModel) {
        this.menuItem = menuItem
        initShoppingCart()
    }

    fun getTitle(): String = menuItem.title

    fun getMenuItemDescription(): String = menuItem.description

    fun getMenuItemImageUrl(): String? = menuItem.image

    fun getMenuItemPrice(): String = menuItem.getFormattedPrice()

    fun addToShoppingCart(comment: String) {
        shoppingCart.addItem(menuItemMapper.mapFromModel(menuItem), comment)
    }
    
}