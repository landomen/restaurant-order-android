package si.lanisnik.restaurantorder.ui.order.history

import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.order.GetHistoryOrders

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListViewModel(private val getHistoryOrders: GetHistoryOrders) : ViewModel() {

    override fun onCleared() {
        getHistoryOrders.dispose()
        super.onCleared()
    }

    fun initialize() {
        fetchOrders()
    }

    private fun fetchOrders() {
        getHistoryOrders.execute(Consumer {

        }, Consumer {

        })
    }
}