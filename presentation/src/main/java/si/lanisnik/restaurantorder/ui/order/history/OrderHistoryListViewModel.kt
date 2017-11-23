package si.lanisnik.restaurantorder.ui.order.history

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.order.GetHistoryOrders
import si.lanisnik.restaurantorder.mapper.OrderHistoryModelMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.order.model.OrderHistoryModel

/**
 * Created by Domen Lanišnik on 20/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderHistoryListViewModel(private val getHistoryOrders: GetHistoryOrders,
                                private val orderMapper: OrderHistoryModelMapper) : ViewModel() {

    private val ordersLiveData: MutableLiveData<Resource<List<OrderHistoryModel>>> = MutableLiveData()

    override fun onCleared() {
        getHistoryOrders.dispose()
        super.onCleared()
    }

    fun getOrdersObservable(): LiveData<Resource<List<OrderHistoryModel>>> = ordersLiveData

    fun initialize() {
        fetchOrders()
    }

    fun onOrderSelected(orderId: Int) {

    }

    private fun fetchOrders() {
        ordersLiveData.postValue(Resource.loading())
        getHistoryOrders.execute(Consumer {
            ordersLiveData.postValue(Resource.success(it.map { orderMapper.mapToModel(it) }))
        }, Consumer {
            ordersLiveData.postValue(Resource.error())
        })
    }
}