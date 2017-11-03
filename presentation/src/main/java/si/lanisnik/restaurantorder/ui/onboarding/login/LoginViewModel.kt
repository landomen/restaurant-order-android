package si.lanisnik.restaurantorder.ui.onboarding.login

import android.arch.lifecycle.ViewModel
import si.lanisnik.restaurantorder.ui.base.extensions.isValidEmail
import si.lanisnik.restaurantorder.ui.base.extensions.isValidPassword
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 01/11/2017.
 * domen.lanisnik@gmail.com
 */
class LoginViewModel @Inject constructor() : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }

    fun isInputValid(email: String, password: String): Boolean =
            email.isValidEmail() && password.isValidPassword()


    fun performLogin(email: String, password: String) {

    }
}