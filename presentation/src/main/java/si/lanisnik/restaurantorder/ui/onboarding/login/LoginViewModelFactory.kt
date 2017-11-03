package si.lanisnik.restaurantorder.ui.onboarding.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.LoginCustomer
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginViewModelFactory @Inject constructor(private val loginCustomer: LoginCustomer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(loginCustomer) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}