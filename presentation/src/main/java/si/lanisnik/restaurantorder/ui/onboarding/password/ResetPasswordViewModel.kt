package si.lanisnik.restaurantorder.ui.onboarding.password

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.exception.NotFoundException
import si.lanisnik.restaurantorder.domain.interactor.customer.ResetPassword
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.base.extensions.isValidEmail
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 04/11/2017.
 * domen.lanisnik@gmail.com
 */
class ResetPasswordViewModel @Inject constructor(private val resetPassword: ResetPassword) : ViewModel() {

    private val actionLiveData: MutableLiveData<SimpleResource> = MutableLiveData()
    private val validationLiveData: MutableLiveData<InputError> = MutableLiveData()

    override fun onCleared() {
        resetPassword.unsubscribe()
        super.onCleared()
    }

    fun getActionObservable(): LiveData<SimpleResource> = actionLiveData

    fun getValidationObservable(): LiveData<InputError> = validationLiveData

    fun resetPassword(email: String) {
        if (email.isEmpty()) {
            validationLiveData.postValue(InputError.REQUIRED)
        } else if (!email.isValidEmail()) {
            validationLiveData.postValue(InputError.EMAIL)
        } else {
            performPasswordReset(email)
        }
    }

    private fun performPasswordReset(email: String) {
        actionLiveData.postValue(SimpleResource.loading())
        resetPassword.execute(Action {
            actionLiveData.postValue(SimpleResource.success())
        }, Consumer {
            actionLiveData.postValue(SimpleResource.error(getMessageForError(it)))
        }, ResetPassword.Params(email))
    }

    private fun getMessageForError(t: Throwable): Int = when (t) {
        is NotFoundException -> R.string.reset_password_not_found
        else -> R.string.error_general
    }

}