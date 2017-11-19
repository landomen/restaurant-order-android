package si.lanisnik.restaurantorder.ui.order.send

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.order.CreateOrder
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import si.lanisnik.restaurantorder.ui.address.model.AddressModel
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.order.send.model.SendOrderActionType
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class SendOrderViewModel @Inject constructor(private val getDeliveryAddresses: GetDeliveryAddresses,
                                             private val createOrder: CreateOrder,
                                             private val addressMapper: AddressModelMapper) : ViewModel() {

    private lateinit var lastAction: SendOrderActionType
    private val addressLiveData: MutableLiveData<Resource<List<AddressModel>>> = MutableLiveData()
    private val createLiveData = MutableLiveData<SimpleResource>()
    private var addresses = mutableListOf<AddressModel>()

    override fun onCleared() {
        getDeliveryAddresses.dispose()
        createOrder.unsubscribe()
        super.onCleared()
    }

    fun getAddressesObservable(): LiveData<Resource<List<AddressModel>>> = addressLiveData

    fun getCreateObservable(): LiveData<SimpleResource> = createLiveData

    fun initialize() {
        getAddresses()
    }

    fun createOrder(comment: String) {
        val selectedAddressId = getSelectedAddressId()
        if (selectedAddressId != -1)
            createNewOrder(selectedAddressId, comment)
        else
            createLiveData.postValue(SimpleResource.error(R.string.send_order_no_address_selected))
    }

    fun retry() {
        when (lastAction) {
            is SendOrderActionType.GetAddresses -> initialize()
            is SendOrderActionType.CreateOrder -> {
                val createOrderAction = lastAction as SendOrderActionType.CreateOrder
                createNewOrder(createOrderAction.selectedAddressId, createOrderAction.comment)
            }
        }
    }

    fun onAddressSelected(addressId: Int) {
        addresses = addresses.map {
            it.copy(selected = it.id == addressId)
        }.toMutableList()
        postNewestAddresses()
    }

    private fun getAddresses() {
        addressLiveData.postValue(Resource.loading())
        lastAction = SendOrderActionType.GetAddresses()
        getDeliveryAddresses.execute(Consumer {
            this.addresses = it.map {
                addressMapper.mapToModel(it)
            }.toMutableList()
            postNewestAddresses()
        }, Consumer {
            addressLiveData.postValue(Resource.error())
        })
    }

    private fun createNewOrder(addressId: Int, comment: String) {
        createLiveData.postValue(SimpleResource.loading())
        lastAction = SendOrderActionType.CreateOrder(addressId, comment)
        createOrder.execute(Action {
            createLiveData.postValue(SimpleResource.success())
        }, Consumer {
            createLiveData.postValue(SimpleResource.error(R.string.error_general))
        }, CreateOrder.Params(addressId, comment))
    }

    private fun postNewestAddresses() {
        addressLiveData.postValue(Resource.success(addresses))
    }

    private fun getSelectedAddressId(): Int {
        return addresses.find { it.selected }?.id ?: -1
    }
}