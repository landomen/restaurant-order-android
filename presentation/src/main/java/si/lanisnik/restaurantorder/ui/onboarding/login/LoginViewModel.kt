package si.lanisnik.restaurantorder.ui.onboarding.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.exception.NotAuthorizedException
import si.lanisnik.restaurantorder.domain.interactor.customer.LoginCustomer
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.isValidEmail
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginViewModel @Inject constructor(private val loginCustomer: LoginCustomer) : ViewModel() {

    private val liveData: MutableLiveData<SimpleResource> = MutableLiveData()
    private val validationLiveData: MutableLiveData<InputError> = MutableLiveData()

    override fun onCleared() {
        loginCustomer.unsubscribe()
        super.onCleared()
    }

    fun getLoginObservable(): LiveData<SimpleResource> = liveData

    fun getValidationObservable(): LiveData<InputError> = validationLiveData

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            validationLiveData.postValue(InputError.MISSING)
        } else if (!email.isValidEmail()) {
            validationLiveData.postValue(InputError.EMAIL)
        } else {
            performLogin(email, password)
        }
    }

    private fun performLogin(email: String, password: String) {
        liveData.postValue(SimpleResource.loading())
        loginCustomer.execute(Action {
            liveData.postValue(SimpleResource.success())
        }, Consumer {
            when (it) {
                is NotAuthorizedException -> liveData.postValue(SimpleResource.error(R.string.login_not_authorized))
                else -> liveData.postValue(SimpleResource.error(R.string.error_general))
            }
        }, LoginCustomer.Params(email, password))
    }
}