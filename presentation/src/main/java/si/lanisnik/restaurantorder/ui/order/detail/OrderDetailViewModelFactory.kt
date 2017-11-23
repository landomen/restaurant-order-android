package si.lanisnik.restaurantorder.ui.order.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.order.GetOrderHistoryDetails
import si.lanisnik.restaurantorder.mapper.OrderModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderDetailViewModelFactory @Inject constructor(private val getOrderHistoryDetails: GetOrderHistoryDetails,
                                                      private val orderMapper: OrderModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderDetailViewModel::class.java))
            return OrderDetailViewModel(getOrderHistoryDetails, orderMapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}