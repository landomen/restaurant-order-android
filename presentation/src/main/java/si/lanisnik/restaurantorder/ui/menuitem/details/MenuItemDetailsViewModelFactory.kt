package si.lanisnik.restaurantorder.ui.menuitem.details

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.MenuItemMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 30/10/2017.
 * domen.lanisnik@gmail.com
 */
class MenuItemDetailsViewModelFactory @Inject constructor(private val shoppingCart: ShoppingCart,
                                                          private val mapper: MenuItemMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuItemDetailsViewModel::class.java))
            return MenuItemDetailsViewModel(shoppingCart, mapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}