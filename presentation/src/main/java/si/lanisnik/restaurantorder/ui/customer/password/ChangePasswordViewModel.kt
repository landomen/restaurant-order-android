package si.lanisnik.restaurantorder.ui.customer.password

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import si.lanisnik.restaurantorder.R
import si.lanisnik.restaurantorder.domain.exception.NotAuthorizedException
import si.lanisnik.restaurantorder.domain.interactor.customer.ChangePassword
import si.lanisnik.restaurantorder.ui.base.data.SimpleResource
import si.lanisnik.restaurantorder.ui.onboarding.model.InputError
import javax.inject.Inject

/**
 * Created by Domen Lanišnik on 05/11/2017.
 * domen.lanisnik@gmail.com
 */
class ChangePasswordViewModel @Inject constructor(private val changePassword: ChangePassword) : ViewModel() {

    private val actionLiveData = MutableLiveData<SimpleResource>()
    private val validationLiveData: MutableLiveData<InputError> = MutableLiveData()

    override fun onCleared() {
        changePassword.unsubscribe()
        super.onCleared()
    }

    fun getActionObservable(): LiveData<SimpleResource> = actionLiveData

    fun getValidationObservable(): LiveData<InputError> = validationLiveData

    fun changePassword(currentPassword: String, newPassword: String, newPasswordConfirmation: String) {
        if (currentPassword.isEmpty() || newPassword.isEmpty() || newPasswordConfirmation.isEmpty()) {
            validationLiveData.postValue(InputError.MISSING)
        } else if (newPassword != newPasswordConfirmation) {
            validationLiveData.postValue(InputError.PASSWORD_CONFIRMATION)
        } else {
            performPasswordChange(currentPassword, newPassword)
        }
    }

    private fun performPasswordChange(currentPassword: String, newPassword: String) {
        actionLiveData.postValue(SimpleResource.loading())
        changePassword.execute(Action {
            actionLiveData.postValue(SimpleResource.success())
        }, Consumer {
            actionLiveData.postValue(SimpleResource.error(getMessageForError(it)))
        }, ChangePassword.Params(currentPassword, newPassword))
    }

    private fun getMessageForError(t: Throwable): Int = when (t) {
        is NotAuthorizedException -> R.string.profile_change_password_incorrect_old
        else -> R.string.error_general
    }
}