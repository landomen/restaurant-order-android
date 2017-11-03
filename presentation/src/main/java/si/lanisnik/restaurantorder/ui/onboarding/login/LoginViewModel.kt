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
import si.lanisnik.restaurantorder.ui.base.extensions.isValidPassword
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginViewModel @Inject constructor(private val loginCustomer: LoginCustomer) : ViewModel() {

    private val liveData: MutableLiveData<SimpleResource> = MutableLiveData()

    override fun onCleared() {
        loginCustomer.unsubscribe()
        super.onCleared()
    }

    fun getLoginObservable(): LiveData<SimpleResource> = liveData

    fun isInputValid(email: String, password: String): Boolean =
            email.isValidEmail() && password.isValidPassword()

    fun performLogin(email: String, password: String) {
        liveData.postValue(SimpleResource.loading())
        loginCustomer.execute(Action {
            liveData.postValue(SimpleResource.success())
        }, Consumer { t ->
            if (t is NotAuthorizedException)
                liveData.postValue(SimpleResource.error(R.string.login_not_authorized))
            else
                liveData.postValue(SimpleResource.error(R.string.general_error))
        }, LoginCustomer.Params(email, password))
    }
}