package si.lanisnik.restaurantorder.ui.menuitem.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.menuitem.GetMenuItems
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.MenuItemMapper

/**
 * Created by Domen Lanišnik on 08/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemsListViewModelFactory(private val getMenuItems: GetMenuItems,
                                    private val mapper: MenuItemMapper,
                                    private val shoppingCart: ShoppingCart) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuItemListViewModel::class.java))
            return MenuItemListViewModel(getMenuItems, mapper, shoppingCart) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}