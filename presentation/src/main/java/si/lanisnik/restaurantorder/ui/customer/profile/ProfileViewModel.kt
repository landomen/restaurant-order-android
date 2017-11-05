package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomer
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileViewModel @Inject constructor(private val getCustomer: GetCustomer,
                                           private val customerModelMapper: CustomerModelMapper) : ViewModel() {

    private val detailsLiveData = MutableLiveData<Resource<CustomerModel>>()

    override fun onCleared() {
        getCustomer.dispose()
        super.onCleared()
    }

    fun getDetailsObservable(): LiveData<Resource<CustomerModel>> = detailsLiveData

    fun initialize() {
        getCustomerDetails()
    }

    fun retry() {
        getCustomerDetails()
    }

    private fun getCustomerDetails() {
        detailsLiveData.postValue(Resource.loading())
        getCustomer.execute(Consumer {
            detailsLiveData.postValue(Resource.success(customerModelMapper.mapToModel(it)))
        }, Consumer {
            detailsLiveData.postValue(Resource.error())
        })
    }
}