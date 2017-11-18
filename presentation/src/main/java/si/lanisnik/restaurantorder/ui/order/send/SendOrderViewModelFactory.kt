package si.lanisnik.restaurantorder.ui.order.send

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.order.CreateOrder
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 18/11/2017.
 * domen.lanisnik@gmail.com
 */
class SendOrderViewModelFactory @Inject constructor(private val getDeliveryAddresses: GetDeliveryAddresses,
                                                    private val createOrder: CreateOrder,
                                                    private val addressMapper: AddressModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SendOrderViewModel::class.java))
            return SendOrderViewModel(getDeliveryAddresses, createOrder, addressMapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}