package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomer
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileViewModelFactory @Inject constructor(private val getCustomer: GetCustomer,
                                                  private val customerModelMapper: CustomerModelMapper) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(getCustomer, customerModelMapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}