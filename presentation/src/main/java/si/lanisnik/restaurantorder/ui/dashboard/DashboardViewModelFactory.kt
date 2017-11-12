package si.lanisnik.restaurantorder.ui.dashboard

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomerType
import si.lanisnik.restaurantorder.domain.model.order.ShoppingCart
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardViewModelFactory @Inject constructor(private val getCustomerType: GetCustomerType,
                                                    private val shoppingCart: ShoppingCart) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java))
            return DashboardViewModel(getCustomerType, shoppingCart) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}