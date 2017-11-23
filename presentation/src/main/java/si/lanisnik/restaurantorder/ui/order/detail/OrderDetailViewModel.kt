package si.lanisnik.restaurantorder.ui.order.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.order.GetOrderHistoryDetails
import si.lanisnik.restaurantorder.mapper.OrderModelMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.order.model.OrderModel

/**
 * Created by Domen Lanišnik on 23/11/2017.
 * domen.lanisnik@gmail.com
 */
class OrderDetailViewModel(private val getOrderHistoryDetails: GetOrderHistoryDetails,
                           private val orderMapper: OrderModelMapper) : ViewModel() {

    private var orderId: Int = 0
    private val orderLiveData = MutableLiveData<Resource<OrderModel>>()

    override fun onCleared() {
        getOrderHistoryDetails.dispose()
        super.onCleared()
    }

    fun getOrderObservable(): LiveData<Resource<OrderModel>> = orderLiveData

    fun initialize(orderId: Int) {
        this.orderId = orderId
        fetchDetails()
    }

    fun retry() {
        fetchDetails()
    }

    private fun fetchDetails() {
        orderLiveData.postValue(Resource.loading())
        getOrderHistoryDetails.execute(Consumer {
            orderLiveData.postValue(Resource.success(orderMapper.mapToModel(it)))
        }, Consumer {
            orderLiveData.postValue(Resource.error())
        }, GetOrderHistoryDetails.Params(orderId))
    }
}