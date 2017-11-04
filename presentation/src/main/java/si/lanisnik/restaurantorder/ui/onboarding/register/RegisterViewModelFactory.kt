package si.lanisnik.restaurantorder.ui.onboarding.register

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.RegisterCustomer
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.utilities.PhoneNumberValidator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class RegisterViewModelFactory @Inject constructor(private val registerCustomer: RegisterCustomer,
                                                   private val customerModelMapper: CustomerModelMapper,
                                                   private val phoneNumberValidator: PhoneNumberValidator) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(registerCustomer, customerModelMapper, phoneNumberValidator) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}