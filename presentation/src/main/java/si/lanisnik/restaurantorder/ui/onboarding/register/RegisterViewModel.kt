package si.lanisnik.restaurantorder.ui.onboarding.register

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.exception.ConflictException
import si.lanisnik.restaurantorder.domain.interactor.customer.RegisterCustomer
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.isValidEmail
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import si.lanisnik.restaurantorder.ui.utilities.PhoneNumberValidator
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class RegisterViewModel @Inject constructor(private val registerCustomer: RegisterCustomer,
                                            private val customerMapper: CustomerModelMapper,
                                            private val phoneNumberValidator: PhoneNumberValidator) : ViewModel() {

    private val registrationLiveData: MutableLiveData<SimpleResource> = MutableLiveData()
    private val validationLiveData: MutableLiveData<InputError> = MutableLiveData()

    override fun onCleared() {
        registerCustomer.unsubscribe()
        super.onCleared()
    }

    fun getRegistrationObservable(): LiveData<SimpleResource> = registrationLiveData

    fun getValidationObservable(): LiveData<InputError> = validationLiveData

    fun register(firstName: String, lastName: String, address: String, email: String,
                 phoneNumber: String, password: String, passwordConfirmation: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || email.isEmpty()
                || phoneNumber.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            validationLiveData.postValue(InputError.MISSING)
        } else if (!email.isValidEmail()) {
            validationLiveData.postValue(InputError.EMAIL)
        } else if (!phoneNumberValidator.isValid(phoneNumber)) {
            validationLiveData.postValue(InputError.PHONE_NUMBER)
        } else if (password != passwordConfirmation) {
            validationLiveData.postValue(InputError.PASSWORD_CONFIRMATION)
        } else {
            val customer = CustomerModel(0, email, password, firstName, lastName, phoneNumber, address)
            performRegistration(customer)
        }
    }

    private fun performRegistration(customer: CustomerModel) {
        registrationLiveData.postValue(SimpleResource.loading())
        registerCustomer.execute(Action {
            registrationLiveData.postValue(SimpleResource.success())
        }, Consumer {
            when (it) {
                is ConflictException -> registrationLiveData.postValue(SimpleResource.error(R.string.register_conflict))
                else -> registrationLiveData.postValue(SimpleResource.error(R.string.error_general))
            }
        }, customerMapper.mapFromModel(customer))
    }

}