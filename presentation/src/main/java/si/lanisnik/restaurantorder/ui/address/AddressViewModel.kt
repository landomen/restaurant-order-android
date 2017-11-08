package si.lanisnik.restaurantorder.ui.address

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.interactor.address.AddDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.DeleteDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.address.SelectDefaultDeliveryAddress
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressViewModel @Inject constructor(private val getAddressesUseCase: GetDeliveryAddresses,
                                           private val deleteDeliveryAddress: DeleteDeliveryAddress,
                                           private val addDeliveryAddress: AddDeliveryAddress,
                                           private val selectDefaultDeliveryAddress: SelectDefaultDeliveryAddress,
                                           private val addressMapper: AddressModelMapper) : ViewModel() {

    private val addressLiveData: MutableLiveData<Resource<List<AddressModel>>> = MutableLiveData()
    private val updateLiveData = MutableLiveData<SimpleResource>()

    private var addresses = mutableListOf<AddressModel>()

    init {
        loadAddresses()
    }

    override fun onCleared() {
        getAddressesUseCase.dispose()
        deleteDeliveryAddress.unsubscribe()
        selectDefaultDeliveryAddress.unsubscribe()
        super.onCleared()
    }

    fun getObservable(): LiveData<Resource<List<AddressModel>>> = addressLiveData

    fun getUpdateObservable(): LiveData<SimpleResource> = updateLiveData

    fun retry() {
        loadAddresses()
    }

    fun deleteAddress(addressId: Int) {
        updateLiveData.postValue(SimpleResource.loading())
        deleteDeliveryAddress.execute(Action {
            onAddressDeleted(addressId)
        }, Consumer {
            updateLiveData.postValue(SimpleResource.error(R.string.general_retry))
        }, DeleteDeliveryAddress.Params(addressId))
    }

    fun selectAddress(addressId: Int) {
        updateLiveData.postValue(SimpleResource.loading())
        selectDefaultDeliveryAddress.execute(Action {
            updateLiveData.postValue(SimpleResource.success())
        }, Consumer {
            updateLiveData.postValue(SimpleResource.error(R.string.general_retry))
        }, SelectDefaultDeliveryAddress.Params(addressId))
    }

    private fun loadAddresses() {
        addressLiveData.postValue(Resource.loading())
        getAddressesUseCase.execute(Consumer {
            addresses = it.map {
                addressMapper.mapToModel(it)
            }.toMutableList()
            postNewestAddresses()
        }, Consumer {
            addressLiveData.postValue(Resource.error())
        })
    }

    private fun onAddressDeleted(addressId: Int) {
        addresses.removeAll { it.id == addressId }
        updateLiveData.postValue(SimpleResource.success())
        postNewestAddresses()
    }

    private fun postNewestAddresses() {
        addressLiveData.postValue(Resource.success(addresses))
    }

}