package si.lanisnik.restaurantorder.ui.order.success

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 19/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderSuccessViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderSuccessViewModel::class.java))
            return OrderSuccessViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}