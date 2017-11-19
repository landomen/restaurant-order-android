package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomer
import si.lanisnik.restaurantorder.domain.interactor.customer.LogoutCustomer
import si.lanisnik.restaurantorder.domain.interactor.customer.UpdateCustomerProfile
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.utilities.PhoneNumberValidator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileViewModelFactory @Inject constructor(private val getCustomer: GetCustomer,
                                                  private val updateCustomerProfile: UpdateCustomerProfile,
                                                  private val logoutCustomer: LogoutCustomer,
                                                  private val customerModelMapper: CustomerModelMapper,
                                                  private val phoneNumberValidator: PhoneNumberValidator) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(getCustomer, updateCustomerProfile,
                    logoutCustomer, customerModelMapper, phoneNumberValidator) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}