package si.lanisnik.restaurantorder.ui.dashboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomerType
import si.lanisnik.restaurantorder.domain.model.customer.CustomerType
import si.lanisnik.restaurantorder.ui.base.data.Resource
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class DashboardViewModel @Inject constructor(private val getCustomerType: GetCustomerType) : ViewModel() {

    private val fullDashboardLiveData = MutableLiveData<Resource<Boolean>>()

    override fun onCleared() {
        getCustomerType.dispose()
        super.onCleared()
    }

    fun getFullDashboardObservable(): LiveData<Resource<Boolean>> = fullDashboardLiveData

    fun initialize() {
        fullDashboardLiveData.postValue(Resource.loading())
        getCustomerType.execute(Consumer {
            fullDashboardLiveData.postValue(Resource.success(it == CustomerType.LOGGED_IN))
        }, Consumer {
            fullDashboardLiveData.postValue(Resource.error())
        })
    }

}