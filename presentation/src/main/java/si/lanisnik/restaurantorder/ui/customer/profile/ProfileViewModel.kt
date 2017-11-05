package si.lanisnik.restaurantorder.ui.customer.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.exception.ConflictException
import si.lanisnik.restaurantorder.domain.interactor.customer.GetCustomer
import si.lanisnik.restaurantorder.domain.interactor.customer.UpdateCustomerProfile
import si.lanisnik.restaurantorder.domain.model.customer.Customer
import si.lanisnik.restaurantorder.mapper.CustomerModelMapper
import si.lanisnik.restaurantorder.ui.base.data.Resource
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.isValidEmail
import si.lanisnik.restaurantorder.ui.base.utilities.PhoneNumberValidator
import si.lanisnik.restaurantorder.ui.customer.model.CustomerModel
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ProfileViewModel @Inject constructor(private val getCustomer: GetCustomer,
                                           private val updateCustomerProfile: UpdateCustomerProfile,
                                           private val customerModelMapper: CustomerModelMapper,
                                           private val phoneNumberValidator: PhoneNumberValidator) : ViewModel() {

    private val detailsLiveData = MutableLiveData<Resource<CustomerModel>>()
    private val updateLiveData = MutableLiveData<SimpleResource>()
    private val validationLiveData = MutableLiveData<InputError>()
    private lateinit var customer: Customer

    override fun onCleared() {
        getCustomer.dispose()
        updateCustomerProfile.unsubscribe()
        super.onCleared()
    }

    fun getDetailsObservable(): LiveData<Resource<CustomerModel>> = detailsLiveData

    fun getUpdateObservable(): LiveData<SimpleResource> = updateLiveData

    fun getValidationObservable(): LiveData<InputError> = validationLiveData

    fun initialize() {
        getCustomerDetails()
    }

    fun retry() {
        getCustomerDetails()
    }

    fun updateProfile(firstName: String, lastName: String, email: String, phoneNumber: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            validationLiveData.postValue(InputError.MISSING)
        } else if (!email.isValidEmail()) {
            validationLiveData.postValue(InputError.EMAIL)
        } else if (!phoneNumberValidator.isValid(phoneNumber)) {
            validationLiveData.postValue(InputError.PHONE_NUMBER)
        } else {
            val updateCustomer = Customer(customer.id, email, customer.password, firstName, lastName, phoneNumber)
            if (hasProfileChanged(updateCustomer))
                performUpdate(updateCustomer)
            else
                updateLiveData.postValue(SimpleResource.success())
        }
    }

    fun restoreDetails() {
        detailsLiveData.postValue(Resource.success(customerModelMapper.mapToModel(customer)))
    }

    private fun getCustomerDetails() {
        detailsLiveData.postValue(Resource.loading())
        getCustomer.execute(Consumer {
            customer = it
            detailsLiveData.postValue(Resource.success(customerModelMapper.mapToModel(it)))
        }, Consumer {
            detailsLiveData.postValue(Resource.error())
        })
    }

    private fun performUpdate(updateCustomer: Customer) {
        updateLiveData.postValue(SimpleResource.loading())
        updateCustomerProfile.execute(Action {
            customer = updateCustomer
            updateLiveData.postValue(SimpleResource.success())
        }, Consumer {
            it.printStackTrace()
            when (it) {
                is ConflictException -> updateLiveData.postValue(SimpleResource.error(R.string.register_conflict))
                else -> updateLiveData.postValue(SimpleResource.error(R.string.error_general))
            }
        }, updateCustomer)
    }

    private fun hasProfileChanged(updateCustomer: Customer): Boolean {
        return updateCustomer.firstName != customer.firstName ||
                updateCustomer.lastName != customer.lastName ||
                updateCustomer.email != customer.email ||
                updateCustomer.phoneNumber != customer.phoneNumber
    }
}