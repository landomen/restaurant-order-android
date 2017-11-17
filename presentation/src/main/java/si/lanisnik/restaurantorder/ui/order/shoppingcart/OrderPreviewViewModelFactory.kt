package si.lanisnik.restaurantorder.ui.order.shoppingcart

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.order.ShoppingCart
import si.lanisnik.restaurantorder.mapper.SelectedMenuItemModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 12/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderPreviewViewModelFactory @Inject constructor(private val shoppingCart: ShoppingCart,
                                                       private val mapper: SelectedMenuItemModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderPreviewViewModel::class.java))
            return OrderPreviewViewModel(shoppingCart, mapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}