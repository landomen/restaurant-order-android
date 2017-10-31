package si.lanisnik.restaurantorder.menuitem.details

import android.arch.lifecycle.ViewModel
import si.lanisnik.restaurantorder.menuitem.model.MenuItemModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModel @Inject constructor() : ViewModel() {

    private lateinit var menuItem: MenuItemModel

    fun initialize(menuItem: MenuItemModel) {
        this.menuItem = menuItem
    }

    fun getTitle(): String = menuItem.title

    fun getMenuItemDescription(): String = menuItem.description

    fun getMenuItemImageUrl(): String? = menuItem.image

    fun getMenuItemPrice(): String = menuItem.getFormattedPrice()

}