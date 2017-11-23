package si.lanisnik.restaurantorder.ui.order.history

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.order.GetHistoryOrders
import si.lanisnik.restaurantorder.mapper.OrderHistoryModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListViewModelFactory @Inject constructor(private val getHistoryOrders: GetHistoryOrders,
                                                           private val orderMapper: OrderHistoryModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderHistoryListViewModel::class.java))
            return OrderHistoryListViewModel(getHistoryOrders, orderMapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}