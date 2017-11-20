package si.lanisnik.restaurantorder.ui.order.history

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderHistoryListViewModel::class.java))
            return OrderHistoryListViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}