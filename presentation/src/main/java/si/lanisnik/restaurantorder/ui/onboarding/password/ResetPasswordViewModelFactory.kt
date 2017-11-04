package si.lanisnik.restaurantorder.ui.onboarding.password

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.ResetPassword
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class ResetPasswordViewModelFactory @Inject constructor(private val resetPassword: ResetPassword) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResetPasswordViewModel::class.java))
            return ResetPasswordViewModel(resetPassword) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}