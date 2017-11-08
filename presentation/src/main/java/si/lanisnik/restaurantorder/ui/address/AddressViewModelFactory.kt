package si.lanisnik.restaurantorder.ui.address

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.address.AddDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.DeleteDeliveryAddress
import si.lanisnik.restaurantorder.domain.interactor.address.GetDeliveryAddresses
import si.lanisnik.restaurantorder.domain.interactor.address.SelectDefaultDeliveryAddress
import si.lanisnik.restaurantorder.mapper.AddressModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 08/11/2017.
 * domen.lanisnik@gmail.com
 */
class AddressViewModelFactory @Inject constructor(private val getDeliveryAddresses: GetDeliveryAddresses,
                                                  private val deleteDeliveryAddress: DeleteDeliveryAddress,
                                                  private val addDeliveryAddress: AddDeliveryAddress,
                                                  private val selectDefaultDeliveryAddress: SelectDefaultDeliveryAddress,
                                                  private val mapper: AddressModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressViewModel::class.java))
            return AddressViewModel(getDeliveryAddresses, deleteDeliveryAddress,
                    addDeliveryAddress, selectDefaultDeliveryAddress, mapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}