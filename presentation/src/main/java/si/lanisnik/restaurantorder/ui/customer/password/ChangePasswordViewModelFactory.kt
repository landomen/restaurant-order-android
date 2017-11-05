package si.lanisnik.restaurantorder.ui.customer.password

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import si.lanisnik.restaurantorder.domain.interactor.customer.ChangePassword
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ChangePasswordViewModelFactory @Inject constructor(private val changePassword: ChangePassword) :
        ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangePasswordViewModel::class.java))
            return ChangePasswordViewModel(changePassword) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}